package com.elleined.emailsenderapi.otp;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
public class OTPController {
    private final OTPService otpService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OTPMessage send(@RequestParam("receiver") String receiver,
                           @RequestParam("subject") String subject,
                           @RequestParam(value = "plusExpirationSeconds", defaultValue = "60") int plusExpirationSeconds) throws MessagingException {

        return otpService.send(receiver, subject, plusExpirationSeconds);
    }
}
