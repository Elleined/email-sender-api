package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.Message;

public interface EmailService<T extends Message> {
    void send(T t);
}
