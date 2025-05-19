package com.elleined.emailsenderapi.otp;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface OTPService {
    OTPMessage send(@Email @NotBlank String receiver,
                    @NotBlank String subject,
                    @Positive int plusExpirationSeconds) throws MessagingException;
}
