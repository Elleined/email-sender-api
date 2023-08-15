package com.elleined.emailsender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPMessage extends Message {

    private LocalTime expiration;

    private Integer otp;

    private Integer fieldOTP;

    @Builder
    public OTPMessage(@Email @NotBlank String receiver, LocalTime expiration, Integer otp, Integer fieldOTP) {
        super(receiver);
        this.expiration = expiration;
        this.otp = otp;
        this.fieldOTP = fieldOTP;
    }
}
