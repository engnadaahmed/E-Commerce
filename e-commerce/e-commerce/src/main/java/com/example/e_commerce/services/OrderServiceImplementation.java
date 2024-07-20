package com.example.e_commerce.services;

import com.example.e_commerce.entity.*;
import com.example.e_commerce.exceptions.OrderException;
import com.example.e_commerce.reposatory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation  implements OrderService{
  //  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);

    private OrderRepository orderRepository;
    private CartService cartService;
    private AddressReposatory  addressReposatory;
    private UserRepository  userRepository;
    private OrderItemRepo  orderItemRepo;
    private OrderItemService orderItemService;
   // private CartRepository cartRepository;


    //private  ProductService productService;




    public OrderServiceImplementation( CartService cartItemService, OrderItemRepo  orderItemRepo,OrderRepository orderRepository,AddressReposatory  addressReposatory,OrderItemService orderItemService,UserRepository  userRepository){

        this.cartService=cartItemService;
        this.orderItemService=orderItemService;
        this.orderItemRepo=orderItemRepo;
        this.orderRepository=orderRepository;
        this.addressReposatory=addressReposatory;
        this.userRepository=userRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
       // logger.debug("Creating order for user: {}", user);

        shippingAddress.setUser(user);
        Address address=addressReposatory.save( shippingAddress);
       // logger.debug("Shipping address saved: {}", address);

        user.getAddress().add(address);
userRepository.save(user);
       // logger.debug("User updated with new address");
        Cart cart=cartService.findUserCart(user.getId());
        List<OrdeItem> orderItems=new ArrayList<>();
        for(CartItem item:cart.getCartItems()){
         OrdeItem orderItem=new  OrdeItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            OrdeItem createdOrderItem=orderItemRepo.save(orderItem);
            orderItems.add(createdOrderItem);
           // logger.debug("Order item saved: {}", createdOrderItem);
        }
        Order createOrder=new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setDiscount(cart.getDiscount());
        createOrder.setTotalItem(cart.getTotalItem());
        createOrder.setShippingAddress(address);
        createOrder.setOrderDate(LocalDateTime.now());
        createOrder.setOrderStatus("PENDING");
        createOrder.getPaymentDetails().setStatus("PENDING");
        createOrder.setCreatedAt(LocalDateTime.now());
        Order savedOrder=orderRepository.save(createOrder);
       // logger.info("Order saved: {}", savedOrder);

        for(OrdeItem item:orderItems){
            item.setOrder(savedOrder);
            orderItemRepo.save(item);
           // logger.debug("Order item updated with order: {}", item);
        }

   return savedOrder;

    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> otp=orderRepository.findById(orderId);
        if(otp.isPresent()){

            return  otp.get();
        }
        throw new OrderException("order not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
       List<Order> orders=orderRepository.getUsersOrder(userId);
       return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
       Order order=findOrderById(orderId);
       order.setOrderStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("SHIPPED");

        return orderRepository.save(order);
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CANCELED");

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order deleteOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
        return order;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("DELIVERED");

        return orderRepository.save(order);
    }
}
