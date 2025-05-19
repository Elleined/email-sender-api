package com.elleined.emailsenderapi.simple;

import com.elleined.emailsenderapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping
public class MailController {
    private final EmailService emailService;

    @PostMapping("/simple-mail")
    public void send(@Valid @RequestBody MessageRequest messageRequest) throws MessagingException {
        emailService.send(messageRequest);
    }

    @PostMapping("/attachment-mail")
    public void send(@Valid @RequestPart("messageRequest") MessageRequest messageRequest,
                     @RequestPart("attachment") MultipartFile attachment) throws MessagingException, IOException {
        emailService.send(messageRequest, attachment);
    }
}
