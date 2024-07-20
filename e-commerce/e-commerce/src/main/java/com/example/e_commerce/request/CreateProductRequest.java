package com.example.e_commerce.request;

import com.example.e_commerce.entity.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductRequest {
   // public String getSecond;
   // private String name;
    private String TopLevelCategory;
    private String SecondLavelCategory;
    private String ThirdLavelCategory;


    private String title;
    private String color;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPersent;
    private Integer quantity;
    private String imageUrl;
    private String brand;


    private Set<Size> size=new HashSet<>();

}
