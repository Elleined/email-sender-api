package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.Message;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService<T extends Message> {
    void send(T t) throws MessagingException, IOException;
}
