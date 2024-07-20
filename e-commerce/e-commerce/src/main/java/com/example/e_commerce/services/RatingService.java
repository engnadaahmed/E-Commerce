package com.example.e_commerce.services;

import com.example.e_commerce.entity.Rating;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req , User user)throws ProductException;
    public List<Rating> getProductRatings(Long productId);
}
