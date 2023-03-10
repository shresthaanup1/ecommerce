package com.ecommerce.authservices.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("You provided an incorrect password");
    }
}
