package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Orders;
import com.example.jnu_tijian.service.AlipayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AliPayController {

    @Autowired
    AlipayService alipayService;
    // 这里可以注入支付宝支付相关的服务类

    @PostMapping("/alipay/PayForBookingOrder")
    public ResponseObject PayForBookingOrder(@RequestBody Orders orders,
                                             @RequestParam("orderPrice") String orderPrice,
                                             @RequestParam("orderName") String orderName) {
        // 调用支付宝支付服务

        System.out.println("成功调用支付宝");
        System.out.println("订单信息："+orders.toString());
        System.out.println("订单价格:"+orderPrice);
        System.out.println("订单名字:"+orderName);
        return alipayService.PayForBookingOrder(orders,orderPrice,orderName);
    }

    // 支付宝异步通知接口
    @PostMapping(value = "/alipay/notify", consumes = "application/x-www-form-urlencoded", produces = "text/plain")
    public String handleAlipayNotify(HttpServletRequest request) {
        // 调用服务处理通知
        System.out.println("收到支付宝异步通知");
        return alipayService.handleNotify(request);
    }

    // 支付状态查询接口 - 关键修复点
    @GetMapping("/checkOrderStatus")
    public ResponseObject checkOrderStatus(@RequestParam String orderId) {
        return alipayService.checkPaymentStatus(orderId);
    }

}