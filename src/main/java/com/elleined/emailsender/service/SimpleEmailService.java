package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
public class SimpleEmailService implements EmailService<EmailMessage>  {

    @Override
    public void send(EmailMessage emailMessage) {

    }
}
