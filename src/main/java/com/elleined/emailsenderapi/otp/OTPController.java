package com.elleined.emailsenderapi.otp;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OTPController {
    private final OTPService otpService;

    @PostMapping("/otp-mail")
    public OTPMessage send(@RequestParam("receiver") String receiver,
                           @RequestParam("subject") String subject,
                           @RequestParam(value = "plusExpirationSeconds", defaultValue = "60") int plusExpirationSeconds) throws MessagingException {

        return otpService.send(receiver, subject, plusExpirationSeconds);
    }
}
