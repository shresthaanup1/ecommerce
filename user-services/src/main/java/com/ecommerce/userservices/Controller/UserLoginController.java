package com.ecommerce.userservices.controller;

import com.ecommerce.userservices.model.AddUserLoginRequest;
import com.ecommerce.userservices.model.UpdateUserLoginRequest;
import com.ecommerce.userservices.model.UserLogin;
import com.ecommerce.userservices.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserLoginController {
    @Autowired
    private UserloginService userloginService;

    @PostMapping(value ="/Login")
    public ResponseEntity<UserLogin> addUserLogin(@RequestBody AddUserLoginRequest addUserLoginRequest){
        return new ResponseEntity<>(userloginService.addUserLogin(addUserLoginRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/Login")
    public ResponseEntity<List<UserLogin>> listUserLogin() {
        return new ResponseEntity<>(userloginService.listUserLogin(), HttpStatus.OK);
    }
    @GetMapping(value ="/Login/{id}")
    public ResponseEntity<UserLogin> getuserLoginById(@PathVariable Long id){
        return new ResponseEntity<>(userloginService.getUserLoginById(id), HttpStatus.FOUND);
    }
    @DeleteMapping(value ="/Login/{id}")
    public ResponseEntity<HttpStatus>deleteUserLoginById(@PathVariable Long id) {
        userloginService.deleteUserLoginById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value ="/Login")
    public ResponseEntity<UserLogin> updateUserLogin(@RequestBody UpdateUserLoginRequest updateUserLoginRequest){
        return new ResponseEntity<>(userloginService.updateUserLogin(updateUserLoginRequest),HttpStatus.ACCEPTED);
    }
    @PatchMapping(value ="/Login")
    public ResponseEntity<UserLogin> patchUserLogin(@RequestBody UpdateUserLoginRequest updateuserLoginRequest){
         return new ResponseEntity<>(userloginService.patchUserLogin(updateuserLoginRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping(value ="/login/{userName}")
    public ResponseEntity<UserLogin> getUserLoginByUsername(@PathVariable String userName){
        return new ResponseEntity<>(userloginService.getUserLoginByUserName(userName), HttpStatus.OK);
    }
}
