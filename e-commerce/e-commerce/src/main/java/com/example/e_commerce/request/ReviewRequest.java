package com.example.e_commerce.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long productId;
    private String review;
}
