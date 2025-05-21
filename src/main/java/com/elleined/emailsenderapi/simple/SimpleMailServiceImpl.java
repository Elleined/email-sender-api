package com.elleined.emailsenderapi.simple;

import com.elleined.emailsenderapi.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SimpleMailServiceImpl implements SimpleMailService {
    private final MailService mailService;
    
    @Override
    public void send(@Email @NotBlank String receiver,
                     @NotBlank String subject,
                     @NotBlank String message) throws MessagingException {

        mailService.send(receiver, subject, message);
        log.debug("Sending simple mail success!");
    }
}
