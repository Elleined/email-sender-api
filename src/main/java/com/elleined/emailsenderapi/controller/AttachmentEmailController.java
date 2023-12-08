package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.dto.EmailAttachmentMessage;
import com.elleined.emailsenderapi.service.AttachmentEmailService;
import com.elleined.emailsenderapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sendMailWithAttachment")
@RequiredArgsConstructor
public class AttachmentEmailController {

    private final AttachmentEmailService attachmentEmailService;

    @PostMapping
    public void sendMailWithAttachment(@RequestPart("attachment") MultipartFile attachment,
                                       @Valid @RequestPart("attachmentMessage") EmailAttachmentMessage attachmentMessage) throws MessagingException, IOException {
        attachmentEmailService.send(attachmentMessage, attachment);
    }
}
