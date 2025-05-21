package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface MailService {
    void send(@Email @NotBlank String receiver,
              @NotBlank String subject,
              @NotBlank String message) throws MessagingException;

    void send(@Email @NotBlank String receiver,
              @NotBlank String subject,
              @NotBlank String message,
              @NotBlank String attachment,
              byte[] bytes) throws MessagingException;
}
