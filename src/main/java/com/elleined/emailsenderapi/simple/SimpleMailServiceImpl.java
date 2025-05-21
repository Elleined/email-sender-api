package com.elleined.emailsenderapi.simple;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SimpleMailServiceImpl implements SimpleMailService {
    private final JavaMailSender mailSender;
    
    @Value("${MAIL_USERNAME}")
    private String sender;
    
    @Override
    public void send(@Email @NotBlank String receiver,
                     @NotBlank String subject,
                     @NotBlank String message) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);

        mailSender.send(mimeMessage);
        log.debug("Sending simple mail success!");
    }
}
