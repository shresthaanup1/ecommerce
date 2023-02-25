package com.ecommerce.productservices.security.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    //@Autowired
    //private RestTemplate restTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");// Bearer JWT token

        //if client is registering for the first time we do not have JWT token so no need to verify
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", ""); // JWT token

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Authorization", header);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        //restTemplate.exchange("localhost:9897/validateToken", HttpMethod.GET,entity, List.class).getBody();

        String output = restTemplate.exchange("http://localhost:9897/validateToken", HttpMethod.GET,entity, String.class).getBody();
        System.out.println(output);
        //call to verify the token

        if(output.equals("Token is fine.")){
            //get user from token
            String user = restTemplate.exchange("http://localhost:9897/getUserFromToken", HttpMethod.GET,entity, String.class).getBody();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else {
            throw new RuntimeException("invalid token.");
        }

        //Create authentication object to set in Application Context
        //this is wrong constructor which may throw null pointer exception
       //  Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        //Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        //SecurityContextHolder.getContext().setAuthentication(authentication);

        //After successful authentication we go to next filter which is operation requested by client
        filterChain.doFilter(request, response);


    }
}
