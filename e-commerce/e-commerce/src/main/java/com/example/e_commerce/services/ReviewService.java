package com.example.e_commerce.services;

import com.example.e_commerce.entity.Review;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review creatReview(ReviewRequest req, User user)throws ProductException;

    public List<Review> getAllReview(Long productId);
}
