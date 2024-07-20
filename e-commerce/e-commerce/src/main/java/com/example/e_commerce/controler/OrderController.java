package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Address;
import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.OrderException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.services.OrderService;
import com.example.e_commerce.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        logger.info("Received request to create order with JWT: {}", jwt);
        User user=userService.findUserProfileByJwt(jwt);
        logger.debug("User found: {}", user);
        Order order=orderService.createOrder(user, shippingAddress);

        logger.info("Order created successfully: {}", order);


        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Order>>usersOrderHistory(
            @RequestHeader("Authorization") String jwt) throws UserException {
        logger.info("Received request to get user order history with JWT: {}", jwt);
        User user = userService.findUserProfileByJwt(jwt);

        List<Order> orders = orderService.usersOrderHistory(user.getId());
        logger.info("Order history retrieved for user: {}", user.getId());
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order>findOrderByld(
            @PathVariable("id") Long orderld,
            @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user=userService.findUserProfileByJwt(jwt);
        Order order =orderService.findOrderById(orderld);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

}
