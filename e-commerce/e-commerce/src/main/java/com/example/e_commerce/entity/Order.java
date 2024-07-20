package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private String orderId;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL )
    private List<OrdeItem> orderItems=new ArrayList();

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @OneToOne
    private  Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails() ;

    private double totalPrice;

    private Integer totalDiscountedPrice;
    private Integer discount;

    private  String orderStatus;

   private int totalItem;

    private LocalDateTime createdAt;


    public Order() {

    }
}
