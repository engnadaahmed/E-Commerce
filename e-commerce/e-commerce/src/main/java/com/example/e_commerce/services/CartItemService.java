package com.example.e_commerce.services;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.CartItem;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.UserException;

import java.util.List;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

   // public List<CartItem> getAllCartItems();

    public CartItem isCartItemExist(Cart cart, Product product,String size, Long userId)throws CartItemException;

    public CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
