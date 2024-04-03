package com.elleined.emailsenderapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OTPMessageDTO {
    private String receiver;

    private String subject;

    private int otp;

    private LocalDateTime expiration;

    @Builder
    public OTPMessageDTO(String receiver, String subject, int otp, LocalDateTime expiration) {
        this.receiver = receiver;
        this.subject = subject;
        this.otp = otp;
        this.expiration = expiration;
    }
}
