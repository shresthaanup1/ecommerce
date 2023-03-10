package com.ecommerce.authservices.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ecommerce.authservices.exception.AuthorizationHeaderNotFoundException;
import com.ecommerce.authservices.exception.IncorrectPasswordException;
import com.ecommerce.authservices.exception.JsonParameterNotValidException;
import com.ecommerce.authservices.feignclients.CustomFeignClient;
import com.ecommerce.authservices.model.JwtResponse;
import com.ecommerce.authservices.model.Roles;
import com.ecommerce.authservices.security.manager.CustomAuthenticationManager;
import com.ecommerce.authservices.model.LoginRequest;
import com.ecommerce.authservices.user.User;
import com.ecommerce.authservices.user.UserService;
import com.ecommerce.authservices.utilities.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;
    @Autowired
    private CustomFeignClient customFeignClient;


    @PostMapping("/getToken")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() != null && loginRequest.getPassword() != null) {

            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            try {
                authentication = customAuthenticationManager.authenticate(authentication);
            } catch (BadCredentialsException e) {
                throw new IncorrectPasswordException();
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwtToken = jwtUtility.generateJwtToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwtToken));
        }else{
            throw new JsonParameterNotValidException("username/password");
        }
    }

    @GetMapping("/getUserFromToken")
    public ResponseEntity<?> getUserFromToken(@RequestHeader(value ="Authorization", required=false) String authHeader) {
        if (authHeader != null) {
            String jwtToken = authHeader.substring(7);
            //first try to validate token if token is valid then only get username from token
            try{
                jwtUtility.validateJwtToken(jwtToken);
            }catch (TokenExpiredException e) {
               throw new TokenExpiredException("Token expired", Instant.now());
            } catch (JWTVerificationException e) {
               throw new JWTVerificationException("Invalid token");
        }
            return new ResponseEntity<>(jwtUtility.getUsernameFromJwtToken(jwtToken), HttpStatus.OK);
        }else {
            throw new AuthorizationHeaderNotFoundException();
        }
    }

    @GetMapping("/validateToken")
    public ResponseEntity<?> getUser(@RequestHeader(value ="Authorization", required = false) String authHeader) {
        if (authHeader != null) {

        // Extract JWT token from Authorization header
        String jwtToken = authHeader.substring(7);

        // Validate JWT token and extract username
        if (jwtUtility.validateJwtToken(jwtToken)) {
            String username = jwtUtility.getUsernameFromJwtToken(jwtToken);

            // Retrieve user details and return response
            //User user = userService.getUserByUserName(username);`
            //return ResponseEntity.ok(user);
            return ResponseEntity.ok("Token is fine.");
        } else {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            return ResponseEntity.ok("Token is not fine.");
        }
        }else {
            throw new AuthorizationHeaderNotFoundException();
        }
    }

    @GetMapping("test/{id}")
    public ResponseEntity<?> testing(@PathVariable Long id){
        return new ResponseEntity<>(customFeignClient.getRolesById(id),HttpStatus.OK);
    }

}
