package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.dto.OTPMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Qualifier("otpEmailService")
public class OTPEmailService extends BaseEmailService implements EmailService<OTPMessage> {

    private final OTPGeneratorService otpGeneratorService;

    @Override
    public void send(OTPMessage otpMessage) throws MessagingException {
        otpGeneratorService.generateOTP();
        LocalTime expiration = LocalTime.now().plusSeconds(otpMessage.getExpirationInSeconds());
        otpGeneratorService.setExpiration(expiration);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(otpMessage.getReceiver());
        messageHelper.setSubject("OTP Confirmation");

        int timeSpan = otpGeneratorService.getExpiration().getMinute() - LocalTime.now().getMinute();
        String howLong = timeSpan <= 1 ? "minute" : "minutes";
        String messageText = String.format("""
                    %s is your authentication code. For your protection, do not
                    share this code to anyone. Code will be valid for %d %s only
                    """, otpGeneratorService.getOtp(), timeSpan, howLong);
        messageHelper.setText(messageText);

        javaMailSender.send(mimeMessage);
        log.debug("Email with OTP sent successfully!");
    }
}
