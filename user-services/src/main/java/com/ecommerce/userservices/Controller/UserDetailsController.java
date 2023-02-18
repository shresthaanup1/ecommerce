package com.ecommerce.userservices.controller;

import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;
import com.ecommerce.userservices.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserDetailsController {
        @Autowired
        private UserDetailsService userDetailsService;
        @PostMapping(value ="/details")
        public ResponseEntity<UserDetails> addUserDetails(@RequestBody AddUserDetailsRequest addUserDetailsRequest){
            return new ResponseEntity<>(userDetailsService.addUserDetails(addUserDetailsRequest), HttpStatus.OK);
        }
}
