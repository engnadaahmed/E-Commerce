package com.example.e_commerce.controler;

import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private  UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHndeler( @RequestHeader("Authorization") String jwt) throws UserException {
        logger.info("Received request to get user profile");
        logger.debug("Authorization Header: {}", jwt);
        User user=userService.findUserProfileByJwt(jwt);
        logger.info("Returning user profile for email: {}", user.getEmail());
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

    }
}
