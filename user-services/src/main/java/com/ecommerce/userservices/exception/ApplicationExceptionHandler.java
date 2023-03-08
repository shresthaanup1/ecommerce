package com.ecommerce.userservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RolesNotFoundException.class, UserDetailsNotFoundException.class})
   public ResponseEntity<Object> handleResourceNotFoundExcepton(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
    @ExceptionHandler({JsonParameterNotValidException.class})
    public ResponseEntity<Object> JsonParameterNotValidException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    //show user-friendly error if user try to insert same value in the unique column
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> SQLIntegrityConstraintViolationException(SQLException ex) {
        String errorMessage = ex.getMessage();
        if (errorMessage.contains("Duplicate entry")) {
            int startIndex = 0;
            int endIndex = errorMessage.indexOf("'",errorMessage.indexOf("'")+1)+1;
            errorMessage = errorMessage.substring(startIndex, endIndex);
        }
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(errorMessage));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
