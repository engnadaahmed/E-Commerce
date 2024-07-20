package com.example.e_commerce.services;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException, CartItemException;

    public Cart findUserCart(Long userId);
}
