package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.dto.OTPMessage;
import com.elleined.emailsenderapi.service.EmailService;
import com.elleined.emailsenderapi.service.OTPGeneratorService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sendOTPMail")
public class OTPEmailController {
    private final EmailService<OTPMessage> emailService;
    private final OTPGeneratorService otpGeneratorService;

    @Autowired
    public OTPEmailController(@Qualifier("otpEmailService") EmailService<OTPMessage> emailService, OTPGeneratorService otpGeneratorService) {
        this.emailService = emailService;
        this.otpGeneratorService = otpGeneratorService;
    }

    @PostMapping
    public OTPMessage sendOTPMail(@Valid @RequestBody OTPMessage otpMessage)
            throws MessagingException, IOException {

        emailService.send(otpMessage);

        otpMessage.setOtp(otpGeneratorService.getOtp()); // Sets the otp because otp is already generated inside send method
        otpMessage.setExpirationTime(otpGeneratorService.getExpiration()); // Sets the otp expiration becuase the exporation is already generated inside send method
        System.out.println("OTP " + otpMessage);
        return otpMessage;
    }
}
