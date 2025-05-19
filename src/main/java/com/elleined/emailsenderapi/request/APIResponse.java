package com.elleined.emailsenderapi.request;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record APIResponse(
        HttpStatus status,
        String message,
        LocalDateTime timeStamp
) {

    public APIResponse(HttpStatus status, String message) {
        this(status, message, LocalDateTime.now());
    }
}
