package com.elleined.emailsender.controller.newcontroller;

import com.elleined.emailsender.dto.EmailAttachmentMessage;
import com.elleined.emailsender.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendEmailWithAttachment")
public class AttachmentEmailController {

    private final EmailService emailService;

    @Autowired
    public AttachmentEmailController(@Qualifier("attachmentEmailService") EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public EmailAttachmentMessage sendMailWithAttachment(@Valid @RequestBody EmailAttachmentMessage emailAttachmentMessage) {
        emailService.send(emailAttachmentMessage);
        return emailAttachmentMessage;
    }
}
