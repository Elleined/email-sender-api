package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
class SimpleEmailService extends BaseEmailService implements EmailService<EmailMessage>  {

    @Override
    public void send(EmailMessage emailMessage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sender);
            messageHelper.setTo(emailMessage.getReceiver());
            messageHelper.setSubject(emailMessage.getSubject());
            messageHelper.setText(emailMessage.getMessageBody());

            javaMailSender.send(message);
            System.out.println("Sending simple mail success!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Sending simple mail failed!");
        }
    }
}
