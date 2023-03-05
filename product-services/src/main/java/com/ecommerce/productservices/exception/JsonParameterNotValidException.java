package com.ecommerce.productservices.exception;

public class JsonParameterNotValidException extends RuntimeException {
    public JsonParameterNotValidException(String param) {
        super("The parameter: "+ param+ " is missing in the request body.");
    }
}
