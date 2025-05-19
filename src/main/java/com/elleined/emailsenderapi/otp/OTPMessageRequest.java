package com.elleined.emailsenderapi.otp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OTPMessageRequest(
        @Email(message = "Please provide a proper email!")
        @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
        String receiver,

        @NotBlank(message = "Email subject cannot be blank, null, or empty!")
        String subject,

        @Positive(message = "Please provide seconds to be added in expiration time from date time now")
        int plusExpirationSeconds
) { }
