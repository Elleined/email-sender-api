package com.elleined.emailsenderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
public abstract class BaseEmailService {

     @Autowired
     protected JavaMailSender javaMailSender;

     @Value("${spring.mail.username}")
     protected String sender;
}
