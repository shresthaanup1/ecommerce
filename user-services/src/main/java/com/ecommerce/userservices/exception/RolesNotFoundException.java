package com.ecommerce.userservices.exception;

public class RolesNotFoundException extends RuntimeException {
    public RolesNotFoundException(Long id){
        super("The role id "+id+ " does not exist");
    }
}
