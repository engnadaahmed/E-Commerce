package com.example.e_commerce.controler;

import com.example.e_commerce.entity.CartItem;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.response.ApiResponse;
import com.example.e_commerce.services.CartItemService;
import com.example.e_commerce.services.CartItemServiceImplementation;
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
@RequestMapping("/api/cart_item")
public class CartItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService CartItemService;

    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt) throws CartItemException, UserException {

        User user=userService.findUserProfileByJwt(jwt);
        CartItem updateCartItem=CartItemService.updateCartItem(user.getId(),cartItemId,cartItem);
    return  new ResponseEntity<>(updateCartItem, HttpStatus.OK);
    }


    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteItem( @PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws CartItemException, UserException {
        logger.info("Request to delete cart item with ID: {}", cartItemId);
        // Log the received Authorization header
        logger.info("Received Authorization header: {}", jwt);

        User user=userService.findUserProfileByJwt(jwt);
        logger.info("User found: {}", user.getId());
       CartItemService.removeCartItem(user.getId(),cartItemId);
        logger.info("Cart item with ID: {} deleted successfully", cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("item delete from cart");
        res.setStatus(true);
        logger.info("Response sent: {}", res);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
