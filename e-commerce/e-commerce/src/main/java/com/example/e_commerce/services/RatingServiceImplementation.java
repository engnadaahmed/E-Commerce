package com.example.e_commerce.services;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.Rating;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.reposatory.RatingRepository;
import com.example.e_commerce.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class RatingServiceImplementation implements RatingService  {
    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService){

        this.productService= productService;
        this.ratingRepository=ratingRepository;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRatings(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
