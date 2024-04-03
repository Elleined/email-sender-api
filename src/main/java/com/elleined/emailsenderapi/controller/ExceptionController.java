package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.request.APIResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<APIResponse>> handleBindException(BindException ex) {
        List<APIResponse> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new APIResponse(HttpStatus.BAD_REQUEST, errorMessage))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<APIResponse> handleMessagingException(MessagingException ex) {
        var responseMessage = new APIResponse(HttpStatus.SERVICE_UNAVAILABLE, "Cannot send email! Probably theres something wrong with the mail service! " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<APIResponse> handleIOException(IOException ex) {
        var responseMessage = new APIResponse(HttpStatus.NOT_ACCEPTABLE, "Cannot send email with attachement! " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}
