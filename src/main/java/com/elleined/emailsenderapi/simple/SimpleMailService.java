package com.elleined.emailsenderapi.simple;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface SimpleMailService {
    void send(@Email @NotBlank String receiver,
              @NotBlank String subject,
              @NotBlank String message) throws MessagingException;
}
