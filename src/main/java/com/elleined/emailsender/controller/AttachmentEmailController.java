package com.elleined.emailsender.controller;

import com.elleined.emailsender.dto.EmailAttachmentMessage;
import com.elleined.emailsender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sendEmailWithAttachment")
public class AttachmentEmailController {

    private final EmailService<EmailAttachmentMessage> emailService;

    @Autowired
    public AttachmentEmailController(@Qualifier("attachmentEmailService") EmailService<EmailAttachmentMessage> emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public EmailAttachmentMessage sendMailWithAttachment(@Valid @RequestBody EmailAttachmentMessage emailAttachmentMessage)
            throws MessagingException, IOException {

        emailService.send(emailAttachmentMessage);
        return emailAttachmentMessage;
    }
}
