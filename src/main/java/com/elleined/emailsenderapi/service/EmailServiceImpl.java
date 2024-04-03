package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.request.MessageRequest;
import com.elleined.emailsenderapi.request.otp.OTPMessageRequest;
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

    @Value("${app.name}")
    private String appName;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(MessageRequest messageRequest) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.getReceiver());
        messageHelper.setSubject(messageRequest.getSubject());
        messageHelper.setText(messageRequest.getMessageBody());

        javaMailSender.send(mimeMessage);
        log.debug("Sending simple mail success!");
    }

    @Override
    public void send(OTPMessageRequest otpMessageRequest, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(otpMessageRequest.getReceiver());
        messageHelper.setSubject(otpMessageRequest.getSubject());
        messageHelper.setText(message);

        javaMailSender.send(mimeMessage);
        log.debug("Sending otp mail success!");
    }

    @Override
    public void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(messageRequest.getReceiver());
        messageHelper.setSubject(messageRequest.getSubject());
        messageHelper.setText(messageRequest.getMessageBody());

        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), new ByteArrayResource(attachment.getBytes()));

        javaMailSender.send(mimeMessage);
        log.debug("Email with attachment sent successfully!");
    }
}
