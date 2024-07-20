package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exceptions.OrderException;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.CreateProductRequest;
import com.example.e_commerce.response.ApiResponse;
import com.example.e_commerce.services.OrderService;
import com.example.e_commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandeller() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);

    }
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandeller(@PathVariable Long orderId, @RequestHeader("Autheriation")String jwt) throws  OrderException {
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order>shippedOrderHandeller(@PathVariable Long orderId,@RequestHeader("Autheriation")String jwt)throws OrderException {
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order>deliverOrderHandeller(@PathVariable Long orderId,@RequestHeader("Autheriation")String jwt)throws OrderException {
        Order order = orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order>cancelOrderHandeller(@PathVariable Long orderId,@RequestHeader("Autheriation")String jwt)throws OrderException {
        Order order = orderService.canceledOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long productId, @RequestHeader("Autheriation")String jwt) throws OrderException {

       orderService.deleteOrder(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }











}
