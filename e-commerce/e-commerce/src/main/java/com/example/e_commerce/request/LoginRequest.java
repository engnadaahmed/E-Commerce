package com.example.e_commerce.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
