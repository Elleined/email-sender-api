package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.dto.OTPMessageDTO;
import com.elleined.emailsenderapi.request.otp.OTPMessageRequest;
import com.elleined.emailsenderapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OTPMailController {
    private final EmailService emailService;
    private final SecureRandom secureRandom;

    @Value("${APP_NAME}")
    private String appName;

    @PostMapping("/otp-mail")
    public OTPMessageDTO send(@Valid @RequestBody OTPMessageRequest otpMessageRequest) throws MessagingException {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(otpMessageRequest.getPlusExpirationSeconds());
        int otp = secureRandom.nextInt(100_000, 999_999);
        String message = String.format("""
                    To verify your account, please enter the following
                    verification code on %s
                    
                    %d
                    
                    The verification code expires in %s minutes. If you do not
                    request this code, please ignore these message.
                    
                    %s
                    """, appName, otp, expiration, appName);

        emailService.send(otpMessageRequest, message);

        return OTPMessageDTO.builder()
                .receiver(otpMessageRequest.getReceiver())
                .subject(otpMessageRequest.getSubject())
                .expiration(expiration)
                .otp(otp)
                .build();
    }
}
