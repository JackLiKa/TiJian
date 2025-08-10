package com.example.jnu_tijian.service;

import com.example.jnu_tijian.entity.Orders;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.exception.TijianAppException;
import com.example.jnu_tijian.exception.TijianAppExceptionEnum;
import com.example.jnu_tijian.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    public Integer makeOrders(Orders orders) {
        return ordersMapper.makeOrders(orders);
    }

    public Orders checkExistingOrder(Orders orders) {
        return ordersMapper.checkExistingOrder(orders);
    }
    public void UpdateOrderState(Orders orders) {
        System.out.println(orders.toString());
        ordersMapper.updateOrderState(orders);
    }

    //插入订单数据
    public ResponseObject saveOrders(Orders orders){
        //先根据体检预约时期查询订单表，看这个用户当天有没有已经预约过了
        //如果有的话，抛出异常，该用户当天已有预约
        Orders existingOrder = ordersMapper.findByUserIdAndDate(orders);
        if ( existingOrder!= null) {
            System.out.println("用户当天已有预约，不能重复预约"+ existingOrder.toString());
            throw new TijianAppException(TijianAppExceptionEnum.EXISTING_ORDER.getCode(),
                    TijianAppExceptionEnum.EXISTING_ORDER.getMessage());
        } else {
            ordersMapper.insert(orders);
            return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC);
        }
    }
    public String selectOrderIdByUserIdAndstateAndhpIdAndsmIdAndorderDate(String userId, Integer state, Integer hpId, Integer smId, Date orderDate) {
        // 根据用户ID、状态、医院ID和体检套餐ID查询订单ID
        System.out.println("当前传入参数："+
                "userId=" + userId +
                ", state=" + state +
                ", hpId=" + hpId +
                ", smId=" + smId +
                ", orderDate=" + orderDate);
        String orderDateStr = formatDateToYYYYMMDD(orderDate);
        System.out.println("格式化后的订单日期为：" + orderDateStr);
        Integer order = (ordersMapper.selectOrderIdByUserIdAndstateAndhpIdAndsmIdAndorderDate(userId, state, hpId, smId, orderDateStr));
        System.out.println("当前查询的订单ID为："+order);
        if(order == null) {
            System.out.println("没有找到符合条件的订单");
            throw new TijianAppException(TijianAppExceptionEnum.ORDER_NOT_FOUND.getCode(),
                    TijianAppExceptionEnum.ORDER_NOT_FOUND.getMessage());
        }
        return order.toString();

    }

    public static String formatDateToYYYYMMDD(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    public ResponseObject getOrdersByUserId(String userId) {
        List<Orders> odersObject=ordersMapper.getOrdersByUserIdAndState(userId);
        for(Orders orders:odersObject){
            System.out.println(orders.toString());
        }
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC,odersObject);
    }
    public ResponseObject cencelAppointment(Integer appointmentId) {
        System.out.println("前端传来订单ID："+appointmentId);
        //先查询订单状态
        ordersMapper.cencelAppointment(appointmentId);
        return new ResponseObject(ResponseObject.SUCCESS_STATUS,ResponseObject.SUCCESS_DESC);

    }
}
