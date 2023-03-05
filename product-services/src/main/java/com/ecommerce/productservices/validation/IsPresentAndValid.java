package com.ecommerce.productservices.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* @IsPresentAndValid is a custom annotation for validating whether the field is passed or not
* if field is not mentioned in the request body, validation is automatically passed
* if field is mentioned in request body, it cannot have blank value
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsPresentAndValidValidator.class)
public @interface IsPresentAndValid {
    String message() default "Invalid Data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
