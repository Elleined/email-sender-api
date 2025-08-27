package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
public class OTPController {
    private final MailService mailService;
    private final SecureRandom secureRandom;

    @Value("${APP_NAME}")
    private String appName;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OTPMessage send(@NotBlank @RequestParam("receiver") String receiver,
                           @NotBlank @RequestParam("subject") String subject,
                           @Positive @RequestParam(value = "plusExpirationSeconds", defaultValue = "60") int plusExpirationSeconds) throws MessagingException {

        Instant expiration = Instant.now().plusSeconds(plusExpirationSeconds);
        String formattedExpiration = DateTimeFormatter
                .ofPattern("MMMM d yyyy 'at' h:mm:ss a", Locale.ENGLISH)
                .format(expiration);

        int otp = secureRandom.nextInt(100_000, 999_999);
        String message = String.format("""
                To verify your account, please enter the following
                verification code on %s
                
                %d
                
                The verification code expires in %s. If you do not
                request this code, please ignore these message.
                """, appName, otp, formattedExpiration);

        mailService.send(receiver, subject, message);
        return new OTPMessage(receiver, subject, otp, expiration);
    }
}
