package com.micropos.carts.service;

import com.micropos.dto.ItemDto;
import com.micropos.carts.model.Cart;

import java.util.Collection;

public interface CartService {
    boolean deleteProductInCart(Cart cart, String productId);

    boolean emptyCart(Cart cart);

    boolean updateProductInCart(Cart cart, String productId, Integer quantity);

    Collection<ItemDto> getCart(Cart cart);
}
