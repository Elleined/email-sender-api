package com.elleined.emailsenderapi.request.otp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OTPMessageRequest {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;

    @NotBlank(message = "Email subject cannot be blank, null, or empty!")
    private String subject;

    @Positive(message = "Please provide seconds to be added in expiration time from date time now")
    private int plusExpirationSeconds;

    @Builder
    public OTPMessageRequest(String receiver, String subject, int plusExpirationSeconds) {
        this.receiver = receiver;
        this.subject = subject;
        this.plusExpirationSeconds = plusExpirationSeconds;
    }
}
