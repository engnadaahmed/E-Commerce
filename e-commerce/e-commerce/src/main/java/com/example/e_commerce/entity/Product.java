package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    @Column(nullable = false)
    private int price;


    @Column(name = "discounted_price")
    private Integer discountedPrice;

    private Integer discountPersent;

    @Column(nullable = false)
    private  Integer quantity;

    @Column(name = "color")
    private String color;


    @Column(nullable = false)
    private String imageUrl;



    private String name;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();


    @Embedded
    @ElementCollection
   // @CollectionTable(name ="sizes")
    @Column(name ="sizes")
    private Set<Size> sizes = new HashSet<>();

    @OneToMany(mappedBy ="product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews=new ArrayList<>();


    @Column(name = "num_ratings")
    private Integer numRatings;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    private String brand;


    private LocalDateTime createdAt;






    /*
    @CreationTimestamp
    private Date productCreated;

    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

   // @OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   // private Set<Order> order;
  // @ManyToMany
  // Set<Order> order;

    @OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Cart> cart;*/






}
