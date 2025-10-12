package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/attachment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestParam("receiver") String receiver,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message,
                     @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {

        mailService.send(receiver, subject, message, attachment.getOriginalFilename(), attachment.getBytes());
    }

    @PostMapping("/simple")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestParam("receiver") String receiver,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message) throws MessagingException {

        mailService.send(receiver, subject, message);
    }
}
