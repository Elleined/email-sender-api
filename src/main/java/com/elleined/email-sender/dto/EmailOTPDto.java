package com.emailsender.Email.Sender.dto;

import com.emailsender.Email.Sender.constraints.OTP;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class EmailOTPDto {

    @Email
    @NotBlank
    private String receiver;

    private LocalTime expiration;

    private Integer OTP;

    @OTP
    private Integer fieldOTP;
}
