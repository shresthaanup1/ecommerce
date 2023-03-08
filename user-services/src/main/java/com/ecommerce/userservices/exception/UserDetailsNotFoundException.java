package com.ecommerce.userservices.exception;

public class UserDetailsNotFoundException extends RuntimeException {
    public UserDetailsNotFoundException(Long id){
        super("The user id "+id+ " does not exist");
    }
}
