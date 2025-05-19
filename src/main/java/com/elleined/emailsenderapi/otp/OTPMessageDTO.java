package com.elleined.emailsenderapi.otp;

import java.time.LocalDateTime;

public record OTPMessageDTO(
        String receiver,
        String subject,
        int otp,
        LocalDateTime expiration
) { }
