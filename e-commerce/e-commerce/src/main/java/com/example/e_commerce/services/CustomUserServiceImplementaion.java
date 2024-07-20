package com.example.e_commerce.services;

import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.reposatory.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserServiceImplementaion  implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserServiceImplementaion(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found with email"+username);
        }
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),grantedAuthorities);
    }


    /*@Override
    public User findUserById(Long userId) throws UserException {

        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        return null;
    }*/
}
