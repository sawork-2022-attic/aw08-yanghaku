package com.micropos.delivery.repository;


import com.micropos.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class InMemoryOrderRepository implements DeliveryRepository {
    private final HashMap<Integer, String> status = new HashMap<>();

    private final HashMap<Integer, Order> orders = new HashMap<>();

    @Override
    public synchronized String findOrderStatusById(Integer orderId) {
        return status.get(orderId);
    }

    @Override
    public synchronized Order findOrderById(Integer orderId) {
        return orders.get(orderId);
    }

    @Override
    public synchronized boolean AddOrder(Order order, String s) {
        if (orders.containsKey(order.getId())) {
            return false;
        }
        status.put(order.getId(), s);
        orders.put(order.getId(), order);
        return true;
    }

    @Override
    public synchronized boolean updateOrderById(Integer orderId, String s) {
        if (status.containsKey(orderId)) {
            status.put(orderId, s);
            return true;
        } else {
            return false;
        }
    }
}
