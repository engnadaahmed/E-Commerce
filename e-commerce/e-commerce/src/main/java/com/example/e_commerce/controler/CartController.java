package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.request.AddItemRequest;
import com.example.e_commerce.response.ApiResponse;
import com.example.e_commerce.services.CartService;
import com.example.e_commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
//    @Operation(description = "find cart by user id")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        if (cart == null) {
            logger.warn("Cart not found for this user ID: {}", user.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cart>(cart,HttpStatus.OK) ;

    }

    //    @Operation (description = "add item to cart")
    @PutMapping("/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,
                                                    @RequestHeader("Authorization")String jwt) throws UserException, ProductException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);
        ApiResponse res = new ApiResponse();
        res.setMessage("item added to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
