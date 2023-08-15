package com.elleined.emailsender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPMessage extends Message {

    private int expirationInSeconds;
    private int otp;

    public OTPMessage(@Email @NotBlank String receiver, int expirationInSeconds, int otp) {
        super(receiver);
        this.expirationInSeconds = expirationInSeconds;
        this.otp = otp;
    }
}
