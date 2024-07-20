package com.example.e_commerce.services;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.Review;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.reposatory.ReviewRepositary;
import com.example.e_commerce.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReviewServiceImplementation implements ReviewService{
    private ReviewRepositary reviewRepositary;
    private ProductService productService;
    private ProductRepository productRepository;
    public ReviewServiceImplementation(ReviewRepositary reviewRepositary,ProductService productService,ProductRepository productRepository){

        this.reviewRepositary=reviewRepositary;
        this.productService=productService;
        this.productRepository=productRepository;
    }
    @Override
    public Review creatReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepositary.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepositary.getAllProductsReview(productId );
    }
}
