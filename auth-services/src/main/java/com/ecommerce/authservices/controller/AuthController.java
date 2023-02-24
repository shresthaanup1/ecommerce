package com.ecommerce.authservices.controller;

import com.ecommerce.authservices.model.JwtResponse;
import com.ecommerce.authservices.security.manager.CustomAuthenticationManager;
import com.ecommerce.authservices.model.LoginRequest;
import com.ecommerce.authservices.user.User;
import com.ecommerce.authservices.user.UserService;
import com.ecommerce.authservices.utilities.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @PostMapping("/getToken")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {


        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        authentication = customAuthenticationManager.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwtToken = jwtUtility.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
        //return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @GetMapping("/getUserFromToken")
    public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String authHeader) {
        String jwtToken = authHeader.substring(7);
        return new ResponseEntity<>(jwtUtility.getUsernameFromJwtToken(jwtToken), HttpStatus.OK);
    }

    @GetMapping("/validateToken")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {

        // Extract JWT token from Authorization header
        String jwtToken = authHeader.substring(7);

        // Validate JWT token and extract username
        if (jwtUtility.validateJwtToken(jwtToken)) {
            String username = jwtUtility.getUsernameFromJwtToken(jwtToken);

            // Retrieve user details and return response
            User user = userService.getUserByUserName(username);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
