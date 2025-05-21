package com.elleined.emailsenderapi;

import java.time.LocalDateTime;

public record OTPMessage(
        String receiver,
        String subject,
        int otp,
        LocalDateTime expiration
) { }
