package com.emailsender.Email.Sender.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = OTPValidator.class)
public @interface OTP {
    String message() default "OTP is either expired or incorrect";

    //represents additional information about annotation
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
