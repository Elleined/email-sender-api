package com.elleined.emailsenderapi.attachment;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentMailController {
    private final AttachmentMailService attachmentMailService;

    @PostMapping("/attachment-mail")
    public void send(@RequestParam("receiver") String receiver,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message,
                     @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {

        attachmentMailService.send(receiver, subject, message, attachment);
    }
}
