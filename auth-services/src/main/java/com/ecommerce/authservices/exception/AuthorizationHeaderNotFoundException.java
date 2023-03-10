package com.ecommerce.authservices.exception;

public class AuthorizationHeaderNotFoundException extends RuntimeException{
    public AuthorizationHeaderNotFoundException() {
        super("The Authorization Header is missing.");
    }
}
