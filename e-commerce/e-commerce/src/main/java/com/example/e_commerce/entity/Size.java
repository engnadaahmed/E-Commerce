package com.example.e_commerce.entity;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class Size {

    private int quantity;
    private String name;
}
