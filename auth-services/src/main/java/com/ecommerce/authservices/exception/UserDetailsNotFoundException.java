package com.ecommerce.authservices.exception;

public class UserDetailsNotFoundException extends RuntimeException {
    public UserDetailsNotFoundException(String username){
        super("The username "+username+ " does not exist");
    }
}
