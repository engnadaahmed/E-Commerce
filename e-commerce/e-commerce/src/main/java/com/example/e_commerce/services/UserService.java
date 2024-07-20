package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.UserException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.e_commerce.entity.User;

//@Service
public interface UserService {

   // UserDetails LoadUserByUsername(String username)throws UsernameNotFoundException;

    public User findUserById(Long userId)throws UserException;
    public  User findUserProfileByJwt(String jwt)throws UserException;
}
