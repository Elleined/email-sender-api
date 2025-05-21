package com.elleined.emailsenderapi.mail;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MailService {
    void send(@Email @NotBlank String receiver,
              @NotBlank String subject,
              @NotBlank String message) throws MessagingException;

    void send(@Email @NotBlank String receiver,
              @NotBlank String subject,
              @NotBlank String message,
              @NotNull MultipartFile attachment) throws MessagingException, IOException;
}
