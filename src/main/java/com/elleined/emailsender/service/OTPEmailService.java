package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.OTPMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
public class OTPEmailService extends BaseEmailService implements EmailService<OTPMessage> {
    @Override
    public void send(OTPMessage otpMessage) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(sender);
        messageHelper.setTo(otpMessage.getReceiver());
        messageHelper.setSubject("OTP Confirmation");

        int timeSpan = otpMessage.getExpiration().getMinute() - LocalTime.now().getMinute();
        String howLong = timeSpan <= 1 ? "minute" : "minutes";
        String messageText = String.format("""
                    %s is your authentication code. For your protection, do not
                    share this code to anyone. Code will be valid for %d %s only
                    """, otpMessage.getOtp(), timeSpan, howLong);
        messageHelper.setText(messageText);

        javaMailSender.send(message);
        log.debug("Email with OTP sent successfully!");
    }
}
