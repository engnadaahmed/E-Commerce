package com.example.e_commerce.handl_exception;

import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse> handleUserException(UserException ex) {
        logger.error("UserException: {}", ex.getMessage());

        ApiResponse response = new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(CartItemException.class)
    public ResponseEntity<ApiResponse> handleCartItemException(CartItemException ex) {
        logger.error("CartItemException: {}", ex.getMessage());

        ApiResponse response = new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
