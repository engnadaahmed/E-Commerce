package com.example.e_commerce.services;

import com.example.e_commerce.entity.OrdeItem;
import com.example.e_commerce.reposatory.OrderItemRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp implements OrderItemService {
    private OrderItemRepo  orderItemRepo;

    public OrderItemServiceImp(OrderItemRepo  orderItemRepo){
      this.orderItemRepo=orderItemRepo;
    }
    @Override
    public OrdeItem createOrderItem(OrdeItem ordeItem) {
        return  orderItemRepo.save( ordeItem);

    }
}
