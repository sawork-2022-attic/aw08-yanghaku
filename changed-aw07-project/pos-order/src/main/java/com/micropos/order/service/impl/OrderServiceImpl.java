package com.micropos.order.service.impl;

import com.micropos.model.Item;
import com.micropos.model.Order;
import com.micropos.order.repository.OrderRepository;
import com.micropos.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    StreamBridge streamBridge;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setStreamBridge(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(List<Item> items) {
        Order order = new Order();
        order.setItems(items);
        orderRepository.addOrder(order);
        return order;
    }

    @Override
    public Order getOrder(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order deliverOrder(Integer id) {
        Order order = getOrder(id);
        if (order == null) {
            return null;
        }
        orderRepository.deleteOrder(id);
        streamBridge.send("delivery", order);
        System.out.println("delivery order " + id);
        return order;
    }
}
