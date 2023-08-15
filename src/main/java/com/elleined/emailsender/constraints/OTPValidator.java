package com.emailsender.Email.Sender.constraints;

import com.emailsender.Email.Sender.service.OTPGeneratorService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class OTPValidator implements ConstraintValidator<OTP, Integer> {

    @Autowired
    private OTPGeneratorService otpGeneratorService;

    @Override
    public boolean isValid(Integer userInputOTP, ConstraintValidatorContext context) {
        LocalTime expiration = otpGeneratorService.getExpiration();
        System.out.println("expiration " + expiration);
        System.out.println("generated otp " + otpGeneratorService.getOtp());
        if (LocalTime.now().isAfter(expiration)) {
            // Setting the error message
            context.buildConstraintViolationWithTemplate("OTP Expired!").addConstraintViolation();
            System.out.println("OTP Expired!");
            return false;
        }
        if (!otpGeneratorService.getOtp().equals(userInputOTP)) {
            // Setting the error message
            context.buildConstraintViolationWithTemplate("OTP Incorrect!").addConstraintViolation();
            System.out.println("OTP Incorrect!");
            return false;
        }
        return true;
    }
}
