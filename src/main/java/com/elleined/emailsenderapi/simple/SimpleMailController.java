package com.elleined.emailsenderapi.simple;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/simple")
public class SimpleMailController {
    private final SimpleMailService simpleMailService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestParam("receiver") String receiver,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message) throws MessagingException {

        simpleMailService.send(receiver, subject, message);
    }
}
