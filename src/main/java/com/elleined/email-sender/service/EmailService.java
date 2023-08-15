package com.emailsender.Email.Sender.service;

import com.emailsender.Email.Sender.dto.EmailDTO;
import com.emailsender.Email.Sender.dto.EmailOTPDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("spring.mail.username")
    private String sender;

    public void sendSimpleMail(EmailDTO emailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sender);
            messageHelper.setTo(emailDTO.getReceiver());
            messageHelper.setSubject(emailDTO.getSubject());
            messageHelper.setText(emailDTO.getMessageBody());

            mailSender.send(message);
            System.out.println("Sending simple mail success!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Sending simple mail failed!");
        }
    }
    public void sendAttachmentMail(EmailDTO emailDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(sender);
            messageHelper.setTo(emailDTO.getReceiver());
            messageHelper.setSubject(emailDTO.getSubject());
            messageHelper.setText(emailDTO.getMessageBody());

            byte[] bytes = emailDTO.getAttachment().getBytes();
            String fileName = emailDTO.getAttachment().getOriginalFilename();

            ByteArrayResource resource = new ByteArrayResource(bytes);
            messageHelper.addAttachment(Objects.requireNonNull(fileName), resource);

            mailSender.send(message);
            System.out.println("Email with attachment sent successfully!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Sending email with attachment failed!");
        }
    }

    public void sendOTPMail(EmailOTPDto emailOTPDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sender);
            messageHelper.setTo(emailOTPDto.getReceiver());
            messageHelper.setSubject("OTP Confirmation");

            int timeSpan = emailOTPDto.getExpiration().getMinute() - LocalTime.now().getMinute();
            String howLong = timeSpan <= 1 ? "minute" : "minutes";
            String messageText = String.format("""
                    %s is your authentication code. For your protection, do not
                    share this code to anyone. Code will be valid for %d %s only
                    """, emailOTPDto.getOTP(), timeSpan, howLong);
            messageHelper.setText(messageText);

            mailSender.send(message);
            System.out.println("Email with OTP sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Sending email with OTP failed!");
        }
    }
}
