package com.example.e_commerce.exceptions;

public class AuthenticationFailException  extends IllegalArgumentException{
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
