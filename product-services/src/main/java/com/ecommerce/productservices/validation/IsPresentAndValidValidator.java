package com.ecommerce.productservices.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPresentAndValidValidator implements ConstraintValidator<IsPresentAndValid,Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) { //checking if the field has been mentioned in the request body or not, if not mentioned return true
            return true;
        }
        else if(!value.toString().isBlank()){ // checking if the field has any value or not, if has value return true
            return true;
        }
        return false;
    }
}
