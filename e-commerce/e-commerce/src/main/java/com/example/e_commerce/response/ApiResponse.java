package com.example.e_commerce.response;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean status;
    private String message;

    public ApiResponse() {
    }
}
