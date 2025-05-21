package com.elleined.emailsenderapi.otp;

import com.elleined.emailsenderapi.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {
    private final MailService mailService;
    private final SecureRandom secureRandom;

    @Value("${APP_NAME}")
    private String appName;

    @Override
    public OTPMessage send(@Email @NotBlank String receiver,
                           @NotBlank String subject,
                           @Positive int plusExpirationSeconds) throws MessagingException {

        LocalDateTime expiration = LocalDateTime.now().plusSeconds(plusExpirationSeconds);
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
        log.debug("Sending otp mail success!");

        return new OTPMessage(receiver, subject, otp, expiration);
    }
}
