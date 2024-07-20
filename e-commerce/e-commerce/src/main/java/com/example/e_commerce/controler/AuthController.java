package com.example.e_commerce.controler;

import com.example.e_commerce.config.JwtProvider;
import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.request.LoginRequest;
import com.example.e_commerce.response.AuthResponse;
import com.example.e_commerce.services.CartService;
import com.example.e_commerce.services.CustomUserServiceImplementaion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userrepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementaion customUserServiceImplementaion;
    private CartService cartService;

    public AuthController( UserRepository userrepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,  CustomUserServiceImplementaion customUserServiceImplementaion,CartService cartService) {

        this.userrepository = userrepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserServiceImplementaion = customUserServiceImplementaion;
        this.cartService=cartService;
    }
   /* @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandeler(@RequestBody User user)throws UserException {
      String email =user.getEmail();
      String password =user.getPassword();
      String firstString=user.getFirstName();
     String LastNString =user.getLastName();
      User isEmailExist=userrepository.findByEmail(email);
       if(isEmailExist !=null){
           throw new UserException("Email Already WITH ANOTHER ACOUNT  ");
       }
        User createdUser=new User();
       createdUser.setEmail(email);
       createdUser.setPassword(passwordEncoder.encode(password));
       createdUser.setFirstName(firstString);
       createdUser.setLastName(firstString);

        User savedUser=userrepository.save(createdUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =jwtProvider.generateToken(authentication);
        AuthResponse authRespose = new AuthResponse();
        authRespose.setJwt(token);
        authRespose.setMessage("signup sucess");

        return new ResponseEntity<AuthResponse>(authRespose, HttpStatus.CREATED);


}*/
   @PostMapping("/signup")
   public ResponseEntity<AuthResponse> createUserHandeler(@RequestBody User user)throws UserException {
       String email =user.getEmail();
       String password =user.getPassword();
       String firstString=user.getFirstName();
       String LastNString =user.getLastName();
       User isEmailExist=userrepository.findByEmail(email);
       if(isEmailExist !=null){
           throw new UserException("Email Already WITH ANOTHER ACOUNT  ");
       }
       User createdUser=new User();
       createdUser.setEmail(email);
       createdUser.setPassword(passwordEncoder.encode(password));
       createdUser.setFirstName(firstString);
       createdUser.setLastName(firstString);

       User savedUser=userrepository.save(createdUser);

       Cart cart=cartService.createCart(savedUser);
       Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token =jwtProvider.generateToken(authentication);
       AuthResponse authRespose = new AuthResponse();
       authRespose.setJwt(token);
       authRespose.setMessage("signup sucess");

       return new ResponseEntity<AuthResponse>(authRespose, HttpStatus.CREATED);


   }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication= authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authRespose = new AuthResponse();
        authRespose.setJwt(token);
        authRespose.setMessage("signin sucess");
        return new ResponseEntity<AuthResponse>(authRespose,HttpStatus.CREATED);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails= customUserServiceImplementaion.loadUserByUsername(username);

        if (userDetails==null){
            throw new BadCredentialsException("invalid username");

        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
