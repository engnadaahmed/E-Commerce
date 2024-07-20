package com.example.e_commerce.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;

   public AuthResponse(){}
    public AuthResponse(String jwt, String message) {
        super();
        this.jwt = jwt;
        this.message = message;

    }
}
