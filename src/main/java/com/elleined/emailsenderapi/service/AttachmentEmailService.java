package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.dto.EmailAttachmentMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@Qualifier("attachmentEmailService")
public class AttachmentEmailService extends BaseEmailService {

    public void send(EmailAttachmentMessage attachmentMessage, MultipartFile attachment) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(attachmentMessage.getReceiver());
        messageHelper.setSubject(attachmentMessage.getSubject());
        messageHelper.setText(attachmentMessage.getMessageBody());

        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), new ByteArrayResource(attachment.getBytes()));

        javaMailSender.send(message);
        log.debug("Email with attachment sent successfully!");
    }
}
