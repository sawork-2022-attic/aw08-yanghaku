package com.micropos.carts.rest;

import com.micropos.dto.OrderDto;
import com.micropos.api.CartApi;
import com.micropos.dto.ItemDto;
import com.micropos.carts.model.Cart;
import com.micropos.carts.service.CartService;
import com.micropos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartsController implements CartApi {
    Cart cart;
    CartService cartService;

    @LoadBalanced
    RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<OrderDto> checkOutCart() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Item>> entity = new HttpEntity<>(cart.getItems(), headers);
        return restTemplate.postForEntity("http://pos-order/api/order/new", entity, OrderDto.class);
    }

    @Override
    public ResponseEntity<Boolean> deleteProductInCart(String productId) {
        if (cartService.deleteProductInCart(cart, productId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ResponseEntity<Boolean> emptyCart() {
        if (cartService.emptyCart(cart)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ResponseEntity<List<ItemDto>> getCart() {
        return new ResponseEntity<>(new ArrayList<>(cartService.getCart(cart)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> updateProductInCart(String productId, Integer quantity) {
        if (cartService.updateProductInCart(cart, productId, quantity)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
