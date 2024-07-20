package com.example.e_commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Entity
@Data
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(max = 50)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    private int level;
    public Category(){}



    /*@Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> product;*/


}
