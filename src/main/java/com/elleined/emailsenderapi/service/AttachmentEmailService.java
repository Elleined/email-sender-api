package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.dto.EmailAttachmentMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@Qualifier("attachmentEmailService")
public class AttachmentEmailService extends BaseEmailService implements EmailService<EmailAttachmentMessage> {
    @Override
    public void send(EmailAttachmentMessage emailAttachmentMessage) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(emailAttachmentMessage.getReceiver());
        messageHelper.setSubject(emailAttachmentMessage.getSubject());
        messageHelper.setText(emailAttachmentMessage.getMessageBody());

        byte[] bytes = emailAttachmentMessage.getAbsoluteAttachmentFileName().getBytes();
        String fileName = emailAttachmentMessage.getAbsoluteAttachmentFileName();

        ByteArrayResource resource = new ByteArrayResource(bytes);
        messageHelper.addAttachment(Objects.requireNonNull(fileName), resource);

        javaMailSender.send(message);
        log.debug("Email with attachment sent successfully!");
    }
}
