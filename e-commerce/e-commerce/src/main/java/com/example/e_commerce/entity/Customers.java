package com.example.e_commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Set;

//@Entity
//@Data
//@Table(name = "Customers")
public class Customers {
   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 3,message = "user name should have at least 2 char")
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Size(min=11, max=11)
    private String phone;

    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Cart> cart;*/






}
