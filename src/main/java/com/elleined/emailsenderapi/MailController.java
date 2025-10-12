package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    public void send(@NotBlank @RequestParam("receiver") String receiver,
                     @NotBlank @RequestParam("subject") String subject,
                     @NotBlank @RequestParam("message") String message,
                     @NotNull @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {

        mailService.send(receiver, subject, message, attachment.getOriginalFilename(), attachment.getBytes());
    }

    @PostMapping("/simple")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@NotBlank @RequestParam("receiver") String receiver,
                     @NotBlank @RequestParam("subject") String subject,
                     @NotBlank @RequestParam("message") String message) throws MessagingException {

        mailService.send(receiver, subject, message);
    }
}
