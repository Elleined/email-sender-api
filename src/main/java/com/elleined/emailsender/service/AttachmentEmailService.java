package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.EmailAttachmentMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
class AttachmentEmailService extends BaseEmailService implements EmailService<EmailAttachmentMessage> {
    @Override
    public void send(EmailAttachmentMessage emailAttachmentMessage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(sender);
            messageHelper.setTo(emailAttachmentMessage.getReceiver());
            messageHelper.setSubject(emailAttachmentMessage.getSubject());
            messageHelper.setText(emailAttachmentMessage.getMessageBody());

            byte[] bytes = emailAttachmentMessage.getAttachment().getBytes();
            String fileName = emailAttachmentMessage.getAttachment().getOriginalFilename();

            ByteArrayResource resource = new ByteArrayResource(bytes);
            messageHelper.addAttachment(Objects.requireNonNull(fileName), resource);

            javaMailSender.send(message);
            System.out.println("Email with attachment sent successfully!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            System.out.println("Error Occurred! Sending email with attachment failed!");
        }
    }
}
