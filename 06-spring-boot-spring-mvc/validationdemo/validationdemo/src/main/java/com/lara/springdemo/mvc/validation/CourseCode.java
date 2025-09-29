package com.lara.springdemo.mvc.validation;

import com.lara.springdemo.mvc.CourseCodeContraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeContraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // definir curso por defecto
    public String value() default "LARA";

    // definir mensaje de error por defecto
    public String message() default "must start with LARA";

    // definir grupos por defecto
    public Class<?>[] groups() default{};

    // definir payloads por defecto
    public Class<? extends Payload>[] payload() default {};
}
