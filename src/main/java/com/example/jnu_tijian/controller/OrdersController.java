package com.example.jnu_tijian.controller;

import com.example.jnu_tijian.entity.Orders;
import com.example.jnu_tijian.dto.ResponseObject;
import com.example.jnu_tijian.entity.Users;
import com.example.jnu_tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
订单模块的控制器
 */
@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    /**
     * 保存订单数据
     */
    @RequestMapping("/saveOrders")
    public ResponseObject saveOrders(@RequestBody Orders orders){
        System.out.println("医院编号："+orders.getHpId());
        return ordersService.saveOrders(orders);
    }

    @PostMapping("/getOrdersByUserId")
    public ResponseObject getOrdersById(@RequestBody Users users){
        System.out.println("前端传来"+users.toString());
        return ordersService. getOrdersByUserId(users.getUserId());
    }

    @PostMapping("/cencelAppointment")
    public ResponseObject cencelAppointment(@RequestBody Orders orders){
        System.out.println("前端传来订单信息："+orders.getOrderId());
        return ordersService.cencelAppointment(orders.getOrderId());
    }
}
