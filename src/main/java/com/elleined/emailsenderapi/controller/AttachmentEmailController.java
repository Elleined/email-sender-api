package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.dto.EmailAttachmentMessage;
import com.elleined.emailsenderapi.service.AttachmentEmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/send-mail-with-attachment")
@RequiredArgsConstructor
public class AttachmentEmailController {

    private final AttachmentEmailService attachmentEmailService;

    @PostMapping
    public void sendMailWithAttachment(@RequestPart("attachment") MultipartFile attachment,
                                       @Valid @RequestPart("attachmentMessage") EmailAttachmentMessage attachmentMessage) throws MessagingException, IOException {
        attachmentEmailService.send(attachmentMessage, attachment);
    }
}
