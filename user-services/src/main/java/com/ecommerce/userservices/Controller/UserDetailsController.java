package com.ecommerce.userservices.controller;

import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UpdateUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;
import com.ecommerce.userservices.service.UserDetailsService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserDetailsController {
        @Autowired
        private UserDetailsService userDetailsService;

        @PostMapping(value = "/details")
        public ResponseEntity<UserDetails> addUserDetails(@RequestBody AddUserDetailsRequest addUserDetailsRequest) {
                return new ResponseEntity<>(userDetailsService.addUserDetails(addUserDetailsRequest), HttpStatus.OK);
        }

        @GetMapping(value = "/details")
        public ResponseEntity<List<UserDetails>> listUserDetails() {
                return new ResponseEntity<>(userDetailsService.listUserDetails(), HttpStatus.OK);
        }

        @GetMapping(value ="/details/{id}")
        public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable Long id){
                return new ResponseEntity<>(userDetailsService.getUserDetailsById(id), HttpStatus.FOUND);
        }

        @DeleteMapping(value ="/details/{id}")
        public ResponseEntity<HttpStatus>deleteUserDetailsById(@PathVariable Long id){
                userDetailsService.deleteUserDetailsById(id);
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        @PutMapping(value="/details")
        public ResponseEntity<UserDetails>updateUserDetails(@RequestBody UpdateUserDetailsRequest updateUserDetailsRequest){
                return new ResponseEntity<>(userDetailsService.updateUserDetails(updateUserDetailsRequest), HttpStatus.ACCEPTED);
        }
        @PatchMapping(value ="/details")
        public ResponseEntity<UserDetails> patchUserDetails(@RequestBody UpdateUserDetailsRequest updateUserDetailsRequest){
                return new ResponseEntity<>(userDetailsService.patchUserDetails(updateUserDetailsRequest), HttpStatus.ACCEPTED);
        }
}