package com.elleined.emailsenderapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OTPMessageDTO {
    private String receiver;
    private String subject;
    private int otp;
    private LocalDateTime expiration;
}
