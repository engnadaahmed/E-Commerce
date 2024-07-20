package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Rating;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.request.RatingRequest;
import com.example.e_commerce.services.RatingService;
import com.example.e_commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private  RatingService ratingService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
       User user=userService.findUserProfileByJwt(jwt);
       Rating rating=ratingService.createRating(request,user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }



    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Rating>> getProductRatings(@PathVariable Long productId,@RequestHeader("Authorization") String jwt) throws  ProductException,UserException{
        User user=userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductRatings(productId);
        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}
