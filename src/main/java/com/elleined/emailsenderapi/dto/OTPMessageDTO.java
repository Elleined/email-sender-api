package com.elleined.emailsenderapi.dto;

import java.time.LocalDateTime;

public record OTPMessageDTO(
        String receiver,
        String subject,
        int otp,
        LocalDateTime expiration
) { }
