package com.ecommerce.productservices.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("The category id '" + id + "' does not exist in our records");
    }
}
