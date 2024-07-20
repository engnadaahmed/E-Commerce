package com.example.e_commerce.services;

import com.example.e_commerce.entity.Address;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.OrderException;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId)throws OrderException;

   public List<Order> usersOrderHistory(Long userId);

    /* i edit it from vidio*/
    public Order placedOrder(Long orderId)throws OrderException;

    public Order confirmedOrder(Long orderId)throws OrderException;

    public Order shippedOrder(Long orderId)throws OrderException;

   /* i edit it from vidio*/
   public Order canceledOrder(Long orderId)throws OrderException;

    public List<Order>getAllOrders();

    public Order deleteOrder(Long orderId)throws OrderException;
    public Order deliveredOrder(Long orderId)throws OrderException;


}
