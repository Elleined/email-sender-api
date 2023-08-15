package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPMessage extends Message {

    @Positive(message = "Email plusExpirationSeconds cannot be less than 0 or equal to 0!")
    private int plusExpirationSeconds;
    
    private LocalTime expirationTime;
    private int otp;

    @Builder
    public OTPMessage(@Email @NotBlank String receiver, int plusExpirationSeconds) {
        super(receiver);
        this.plusExpirationSeconds = plusExpirationSeconds;
    }
}
