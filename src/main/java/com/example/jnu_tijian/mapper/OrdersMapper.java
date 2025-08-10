package com.example.jnu_tijian.mapper;

import com.example.jnu_tijian.entity.Orders;
import com.example.jnu_tijian.dto.CalendarResponseDto;
import com.example.jnu_tijian.dto.OrdersMapperDto;

import java.util.Date;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Orders orders);

    Orders findByUserIdAndDate(Orders record);

    int updateByPrimaryKey(Orders record);

    int makeOrders(Orders orders);

    Orders checkExistingOrder(Orders orders);

    Integer selectOrderIdByUserIdAndstateAndhpIdAndsmIdAndorderDate(String userId, Integer state, Integer hpId, Integer smId, String orderDate);

    List<CalendarResponseDto> listHospitalAppointmentNumber(List<OrdersMapperDto> ordersMapperDtos);

    void updateOrderState(Orders orders);

    int getStatebyOrderId(Integer orderId);

    List<Orders> getOrdersByUserIdAndState(String userId);

    void cencelAppointment(Integer orderId);
}