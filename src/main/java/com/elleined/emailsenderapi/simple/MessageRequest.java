package com.elleined.emailsenderapi.simple;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MessageRequest(
        @Email(message = "Please provide a proper email!")
        @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
        String receiver,

        @NotBlank(message = "Email subject cannot be blank, null, or empty!")
        String subject,

        @NotBlank(message = "Email Message cannot be blank, null, or empty!")
        String messageBody
) { }
