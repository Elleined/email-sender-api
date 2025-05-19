package com.elleined.emailsenderapi.otp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {
    private final JavaMailSender mailSender;
    private final SecureRandom secureRandom;

    @Value("${MAIL_USERNAME}")
    private String sender;

    @Value("${APP_NAME}")
    private String appName;

    @Override
    public OTPMessage send(@Email @NotBlank String receiver,
                           @NotBlank String subject,
                           @Positive int plusExpirationSeconds) throws MessagingException {

        LocalDateTime expiration = LocalDateTime.now().plusSeconds(plusExpirationSeconds);
        int otp = secureRandom.nextInt(100_000, 999_999);
        String message = String.format("""
                    To verify your account, please enter the following
                    verification code on %s
                    
                    %d
                    
                    The verification code expires in %s minutes. If you do not
                    request this code, please ignore these message.
                    
                    %s
                    """, appName, otp, expiration, appName);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);

        mailSender.send(mimeMessage);
        log.debug("Sending otp mail success!");

        return new OTPMessage(receiver, subject, otp, expiration);
    }
}
