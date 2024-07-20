package com.example.e_commerce.services;

import com.example.e_commerce.config.JwtProvider;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.reposatory.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    public  UserServiceImplementation(UserRepository userRepository ,JwtProvider jwtProvider){
        this.userRepository=userRepository;
        this.jwtProvider=jwtProvider;


    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User>user=userRepository.findById(userId);
        if(user.isPresent()){

            return user.get();
        }
        throw new UserException("user not found with id:" + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        logger.info("Extracting email from JWT");
       String email=jwtProvider.getEmailFromToken(jwt);
        logger.debug("Email extracted from JWT: {}", email);
       User user=userRepository.findByEmail(email);
       if(user==null) {
           logger.error("User not found with email: {}", email);
throw  new UserException("usernot found with email"+email);
       }
        logger.info("User found with email: {}", email);
return  user;
    }
}
