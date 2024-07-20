package com.example.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class OrdeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private String size;

    private int quantity;

    private Integer price;

    private Integer discountedPrice;
    private Long userId;

    private LocalDateTime deliveryDate;

    public OrdeItem(){}
}
