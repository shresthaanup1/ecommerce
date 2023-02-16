package com.ecommerce.userservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RolesNotFoundException.class})
   public ResponseEntity<Object> handleResourceNotFoundExcepton(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler({JsonParameterNotValidException.class})
    public ResponseEntity<Object> JsonParameterNotValidException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
