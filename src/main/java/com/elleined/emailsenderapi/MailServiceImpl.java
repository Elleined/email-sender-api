package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final String sender;

    public MailServiceImpl(JavaMailSender mailSender, @Value("${spring.mail.username}") String sender) {
        this.mailSender = mailSender;
        this.sender = sender;
    }

    @Async
    @Override
    public void send(String receiver,
                     String subject,
                     String message) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);

        mailSender.send(mimeMessage);
    }

    @Async
    @Override
    public void send(String receiver,
                     String subject,
                     String message,
                     String fileName,
                     byte[] bytes) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        messageHelper.addAttachment(fileName, new ByteArrayResource(bytes));

        mailSender.send(mimeMessage);
    }
}
