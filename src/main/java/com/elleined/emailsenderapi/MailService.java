package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface MailService {
    void send(@NotBlank @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") String receiver,
              @NotBlank String subject,
              @NotBlank String message) throws MessagingException;

    void send(@NotBlank @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") String receiver,
              @NotBlank String subject,
              @NotBlank String message,
              @NotNull String fileName,
              byte[] bytes) throws MessagingException;
}
