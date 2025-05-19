package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.simple.MessageRequest;
import com.elleined.emailsenderapi.otp.OTPMessageRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${MAIL_USERNAME}")
    private String sender;

    @Override
    public void send(MessageRequest messageRequest) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.receiver());
        messageHelper.setSubject(messageRequest.subject());
        messageHelper.setText(messageRequest.messageBody());

        javaMailSender.send(mimeMessage);
        log.debug("Sending simple mail success!");
    }

    @Override
    public void send(OTPMessageRequest otpMessageRequest, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(otpMessageRequest.receiver());
        messageHelper.setSubject(otpMessageRequest.subject());
        messageHelper.setText(message);

        javaMailSender.send(mimeMessage);
        log.debug("Sending otp mail success!");
    }

    @Override
    public void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.receiver());
        messageHelper.setSubject(messageRequest.subject());
        messageHelper.setText(messageRequest.messageBody());

        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), new ByteArrayResource(attachment.getBytes()));

        javaMailSender.send(mimeMessage);
        log.debug("Email with attachment sent successfully!");
    }
}
