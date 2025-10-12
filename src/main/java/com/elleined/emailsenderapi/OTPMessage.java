package com.elleined.emailsenderapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record OTPMessage(
        @JsonProperty("receiver") String receiver,
        @JsonProperty("sunject") String subject,
        @JsonProperty("otp") int otp,
        @JsonProperty("expiration") Instant expiration
) { }
