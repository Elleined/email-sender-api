package com.elleined.emailsenderapi;

import java.time.Instant;

public record OTPMessage(
        String receiver,
        String subject,
        int otp,
        Instant expiration
) { }
