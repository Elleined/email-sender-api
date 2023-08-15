package com.elleined.emailsender.controller.newcontroller;

import com.elleined.emailsender.dto.EmailMessage;
import com.elleined.emailsender.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sendSimpleEmail")
public class SimpleEmailController {

    private final EmailService emailService;

    @PostMapping
    public EmailMessage sendSimpleMail(@Valid @RequestBody EmailMessage emailMessage) {
        emailService.send(emailMessage);
        return emailMessage;
    }
}
