package com.elleined.emailsender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
 abstract class BaseEmailService {

    protected JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    protected String sender;
}
