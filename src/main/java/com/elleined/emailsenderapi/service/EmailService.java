package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.dto.Message;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService<T extends Message> {
    void send(T t) throws MessagingException, IOException;
}
