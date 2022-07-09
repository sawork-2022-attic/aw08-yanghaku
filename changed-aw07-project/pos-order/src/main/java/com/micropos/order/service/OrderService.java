package com.micropos.order.service;

import com.micropos.model.Item;
import com.micropos.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();

    Order addOrder(List<Item> items);

    Order getOrder(Integer id);

    Order deliverOrder(Integer id);
}
