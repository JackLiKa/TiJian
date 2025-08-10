package com.example.jnu_tijian.service;


//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.example.jnu_tijian.config.AliPayConfig;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Orders;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.OrdersMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//import static com.alipay.api.AlipayConstants.*;

/**
 * @Author: xiyang
 * @FileName: AlipayServicImpl
 * @Date: Created in 2021/8/6 12:28
 * @Vserion:
 * @Description: TODO
 */
@Service
public class AlipayService{
    @Autowired
    AliPayConfig aliPayConfig;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersMapper ordersMapper;

    public ResponseObject PayForBookingOrder(Orders orders,String orderPrice,String orderName) {


        System.out.println("前端传来参数为："+orders.toString());
        Orders existingOrder = ordersService.checkExistingOrder(orders);
        if(existingOrder!=null&&existingOrder.getState()==1) {
            System.out.println("检测到该订单存在,订单信息为："+existingOrder.toString());
            return new ResponseObject(TijianAppExceptionEnum.EXISTING_ORDER.getCode(),
                    TijianAppExceptionEnum.EXISTING_ORDER.getMessage(), null);
        }else if(existingOrder!=null&&existingOrder.getState()==0){
            System.out.println("这个没支付的订单信息为："+existingOrder.toString());
        } else{
            ordersService.makeOrders(orders);
        }
        String OrderId= ordersService.selectOrderIdByUserIdAndstateAndhpIdAndsmIdAndorderDate(orders.getUserId(),orders.getState(),orders.getHpId(),orders.getSmId(),orders.getOrderDate()).toString();
        ResponseObject  res = PayForObject(orderName,"ORDER_"+OrderId,orderPrice);
        if(res.getStatus() == ResponseObject.SUCCESS_STATUS) {
            ordersService.UpdateOrderState(orders) ;
            return new ResponseObject(ResponseObject.SUCCESS_STATUS, "支付二维码生成成功", res.getData());
        }
        else {
            // 支付失败，可能是配置问题或其他异常
            System.err.println("支付二维码生成失败，原因：" + res.getDesc());
            return new ResponseObject(TijianAppExceptionEnum.SYSTEM_ERROR.getCode(),
                    TijianAppExceptionEnum.SYSTEM_ERROR.getMessage(), null);
        }
    }
    public ResponseObject PayForObject(String OrderName, String OrderId, String OrderPrice) {
        try {
            // 初始化配置时设置全局通知地址
            Config config = aliPayConfig.init();
            // 关键修改：在Config对象中设置notifyUrl
            String notifyUrl = aliPayConfig.getNotifyUrl();
            config.notifyUrl = notifyUrl; // ⭐️ 设置异步通知地址

            System.out.println("支付宝配置信息：" + config);
            System.out.println("异步通知地址：" + notifyUrl); // 确认地址正确

            Factory.setOptions(config);
        } catch (Exception e) {
            System.err.println("初始化支付宝配置失败：" + e.getMessage());
            return new ResponseObject(TijianAppExceptionEnum.INIT_CONFIG_FAILED.getCode(),
                    TijianAppExceptionEnum.INIT_CONFIG_FAILED.getMessage(), null);
        }

        OrderPrice = formatTo2Decimal(OrderPrice);
        try {
            System.out.println("调用支付宝API - 订单: " + OrderName +
                    " | ID: " + OrderId +
                    " | 价格: " + OrderPrice);

            // 使用标准的3参数preCreate方法
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate(OrderName, OrderId, OrderPrice);

            System.out.println("支付宝响应 - code: " + response.code +
                    " | msg: " + response.msg +
                    " | subMsg: " + response.subMsg);

            if (ResponseChecker.success(response)) {
                System.out.println("二维码生成成功: " + response.qrCode);
                Map<String, Object> data = new HashMap<>();
                data.put("qrCodeUrl", response.qrCode);
                data.put("orderId", OrderId);
                data.put("orderName", OrderName);
                data.put("orderPrice", OrderPrice);
                return new ResponseObject(ResponseObject.SUCCESS_STATUS, "支付二维码生成成功", data);
            } else {
                String errorMsg = "支付失败: " + response.subMsg;
                System.err.println(errorMsg);
                return new ResponseObject(TijianAppExceptionEnum.PAY_FAILED.getCode(), errorMsg, null);
            }
        } catch (Exception e) {
            String errorMsg = "系统异常: " + e.getMessage();
            System.err.println(errorMsg);
            return new ResponseObject(TijianAppExceptionEnum.SYSTEM_ERROR.getCode(), errorMsg, null);
        }
    }

    // 封装错误响应
    private ResponseObject failResponse(TijianAppExceptionEnum type, String detail) {
        return new ResponseObject(type.getCode(), type.getMessage() + ":" + detail, null);
    }



//转换价格格式
public static String formatTo2Decimal(String numStr) {
    try {
        // 1. 将字符串转换为BigDecimal
        BigDecimal decimal = new BigDecimal(numStr);
        // 2. 保留2位小数，四舍五入
        BigDecimal result = decimal.setScale(2, RoundingMode.HALF_UP);
        // 3. 转换为字符串（确保不会用科学计数法）
        return result.toPlainString();
    } catch (NumberFormatException e) {
        // 处理非法输入（如非数字字符串）
        throw new IllegalArgumentException("无效的数字格式：" + numStr, e);
    }
}


    public String handleNotify(HttpServletRequest request) {
        System.out.println("收到支付宝异步通知");

        // 将请求参数转为Map
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
            System.out.println("收到的参数名: " + name + ", 参数值: " + request.getParameter(name));
        }

        try {
            // 1. 验证签名
            boolean signValid = Factory.Payment.Common().verifyNotify(params);
            if (!signValid) {
                System.err.println("支付宝异步通知验签失败");
                return "failure";
            }

            // 2. 验证交易状态
            String tradeStatus = params.get("trade_status");
            if (!"TRADE_SUCCESS".equals(tradeStatus) && !"TRADE_FINISHED".equals(tradeStatus)) {
                System.err.println("交易状态不成功: " + tradeStatus);
                return "failure";
            }

            // 3. 获取商户订单号并解析
            String outTradeNo = params.get("out_trade_no");
            if (outTradeNo == null || !outTradeNo.startsWith("ORDER_")) {
                System.err.println("无效的商户订单号: " + outTradeNo);
                return "failure";
            }
            String orderIdStr = outTradeNo.substring(6); // 去掉"ORDER_"前缀
            Integer orderId = Integer.parseInt(orderIdStr);

            // 4. 更新订单状态
            Orders updateOrder = new Orders();
            updateOrder.setOrderId(orderId);
            updateOrder.setState(1); // 已支付状态

            // 使用ordersMapper更新状态
            int updated = ordersMapper.updateByPrimaryKeySelective(updateOrder);
            if (updated > 0) {
                System.out.println("订单状态更新成功: ORDER_" + orderId);
                return "success";
            } else {
                System.err.println("订单状态更新失败: ORDER_" + orderId);
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }


public ResponseObject checkPaymentStatus(String orderId){
    try {
        // 解析订单ID (去掉"ORDER_"前缀)
        if (!orderId.startsWith("ORDER_")) {
            return new ResponseObject(400, "订单号格式错误", null);
        }
        String idStr = orderId.substring(6);

        Integer id = Integer.parseInt(idStr);
        System.out.println("查询订单状态，订单ID: " + id);


        // 通过ordersMapper查询订单状态
        Orders order = ordersMapper.selectByPrimaryKey(id);
        if (order == null) {
            return new ResponseObject(404, "订单不存在", null);
        }else {
            System.out.println("当前订单id: " + order.getOrderId());
            Integer Curstate = ordersMapper.getStatebyOrderId(order.getOrderId());
            System.out.println("当前订单状态: " + Curstate);
            Map<String, Object> data = new HashMap<>();
            data.put("status", Curstate);

            return new ResponseObject(200, "查询成功", data);
        }
    } catch (NumberFormatException e) {
        return new ResponseObject(400, "订单号格式错误", null);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseObject(500, "系统错误", null);
    }
}


}