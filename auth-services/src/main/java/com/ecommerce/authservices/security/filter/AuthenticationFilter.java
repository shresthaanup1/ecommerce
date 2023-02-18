package com.ecommerce.authservices.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.authservices.security.SecurityConstants;
import com.ecommerce.authservices.security.UserCredentials;
import com.ecommerce.authservices.security.manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    CustomAuthenticationManager customAuthenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            //login
            //deserialize the username and password from request to change into User object
            UserCredentials user = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
           // System.out.println(user.getUsername());
           // System.out.println(user.getPassword());

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return customAuthenticationManager.authenticate(authentication);

        } catch (IOException e) {
            //if user passes bad json fields we can't map to User Object
            throw new RuntimeException(e);
        }
        //return super.attemptAuthentication(request,response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
    }

}




