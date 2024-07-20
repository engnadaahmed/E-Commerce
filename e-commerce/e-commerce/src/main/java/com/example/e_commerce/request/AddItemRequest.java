package com.example.e_commerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemRequest {
    private Long productId;
    private String size;
    private int quantity;
    private Integer price;
}
