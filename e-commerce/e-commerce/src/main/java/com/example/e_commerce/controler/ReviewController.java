package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Rating;
import com.example.e_commerce.entity.Review;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.request.ReviewRequest;
import com.example.e_commerce.services.ReviewService;
import com.example.e_commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService  userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user=userService.findUserProfileByJwt(jwt);
        Review review=reviewService.creatReview(request,user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) throws UserException, ProductException {

        List<Review> review = reviewService.getAllReview(productId);
        return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
    }
}
