package com.micropos.order.rest;

import com.micropos.mapper.ItemMapper;
import com.micropos.model.Item;
import com.micropos.api.OrderApi;
import com.micropos.dto.ItemDto;
import com.micropos.dto.OrderDto;
import com.micropos.mapper.OrderMapper;
import com.micropos.model.Order;
import com.micropos.order.service.OrderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController implements OrderApi {

    OrderService orderService;

    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderDto> addOrder(List<ItemDto> items) {
        Order order = orderService.addOrder((List<Item>) itemMapper.toCart(items));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderMapper.toOrderDto(order), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<OrderDto> deliverOrderById(Integer orderId) {
        Order order = orderService.deliverOrder(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderMapper.toOrderDto(order), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Integer orderId) {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderMapper.toOrderDto(order), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<OrderDto>> allOrders() {
        Collection<Order> orders = orderService.getAllOrder();
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>((List<OrderDto>) orderMapper.toOrdersDto(orders), HttpStatus.OK);
        }
    }
}
