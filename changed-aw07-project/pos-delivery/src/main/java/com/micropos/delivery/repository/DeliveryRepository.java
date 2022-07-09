package com.micropos.delivery.repository;

import com.micropos.model.Order;

public interface DeliveryRepository {
    String findOrderStatusById(Integer orderId);

    Order findOrderById(Integer orderId);

    boolean AddOrder(Order order, String status);

    boolean updateOrderById(Integer orderId, String status);
}
