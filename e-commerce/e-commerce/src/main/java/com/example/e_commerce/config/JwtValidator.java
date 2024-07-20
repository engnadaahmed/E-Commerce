package com.example.e_commerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtValidator  extends OncePerRequestFilter {


   /* private Keys keys;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt != null ) {
            jwt= jwt.substring(7);
            try {
                SecretKey key =keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String Email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(Email, null, auths);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception e)
            {
                throw new BadCredentialsException("invalid token......from jwt validator");
            }

//        if (jwt != null && jwt.contains("Bearer")) {
//            jwt = jwt.replace("Bearer ", "");
        }
        filterChain.doFilter(request, response);
    }
*/
   private Keys keys;
    private static final Logger logger = LoggerFactory.getLogger(JwtValidator.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt != null ) {
            jwt= jwt.substring(7);
            logger.debug("Received JWT: {}", jwt);
            try {
                SecretKey key =keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String Email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                logger.debug("Extracted email from JWT: {}", Email);
                logger.debug("Extracted authorities from JWT: {}", authorities);
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication auth = new UsernamePasswordAuthenticationToken(Email, null, auths);

                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.debug("Security context set with authentication: {}", auth);

            }catch (Exception e)
            {
                logger.error("Invalid token from JWT validator", e);
                throw new BadCredentialsException("invalid token......from jwt validator",e);
            }


        }else{ logger.debug("No JWT found in request header");}
        filterChain.doFilter(request, response);
    }

}
