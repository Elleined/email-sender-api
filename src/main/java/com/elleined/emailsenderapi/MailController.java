package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("/attachment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestPart("receiver") String receiver,
                     @RequestPart("subject") String subject,
                     @RequestPart("message") String message,
                     @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {

        mailService.send(receiver, subject, message, attachment);
    }

    @PostMapping("/simple")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestParam("receiver") String receiver,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message) throws MessagingException {

        mailService.send(receiver, subject, message);
    }
}
