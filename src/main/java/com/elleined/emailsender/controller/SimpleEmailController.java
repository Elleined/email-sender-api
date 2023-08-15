package com.elleined.emailsender.controller;

import com.elleined.emailsender.dto.EmailMessage;
import com.elleined.emailsender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sendSimpleEmail")
public class SimpleEmailController {

    private final EmailService<EmailMessage> emailService;

    @PostMapping
    public EmailMessage sendSimpleMail(@Valid @RequestBody EmailMessage emailMessage)
            throws MessagingException, IOException {

        emailService.send(emailMessage);
        return emailMessage;
    }
}
