package com.example.e_commerce.dto;

import javax.validation.constraints.NotNull;

public class AddToCartDto {
    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;
}
