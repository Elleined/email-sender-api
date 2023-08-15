package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPMessage extends Message {

    private int plusExpirationSeconds;
    private LocalTime expirationDate;
    private int otp;

    public OTPMessage(@Email @NotBlank String receiver, int plusExpirationSeconds, int otp) {
        super(receiver);
        this.plusExpirationSeconds = plusExpirationSeconds;
        this.otp = otp;
    }
}
