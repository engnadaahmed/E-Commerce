package com.example.e_commerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class JwtProvider {

   /* private Keys keys;
    SecretKey key =keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken( Authentication auth) {
        String jwt = Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()  + 846000000))
        .claim("email", auth.getName())
        .signWith(key).compact();

        return jwt;
    }
    public String getEmailFromToken(String jwt) {
        jwt =jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String Email = String.valueOf(claims.get("email"));
        return Email;
    }*/
   private Keys keys;
    SecretKey key =keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken( Authentication auth) {
        io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date(new Date().getTime()  + 846000000));
        builder.claim("email", auth.getName());
        builder.signWith(key);
        String jwt = builder.compact();
        return jwt;
    }
    public String getEmailFromToken(String jwt) {
        jwt =jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String Email = String.valueOf(claims.get("email"));
        return Email;
    }
}
