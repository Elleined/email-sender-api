package com.elleined.emailsenderapi.controller;

import com.elleined.emailsenderapi.dto.ResponseMessage;
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
    public ResponseEntity<List<ResponseMessage>> handleBindException(BindException ex) {
        List<ResponseMessage> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessage))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ResponseMessage> handleMessagingException(MessagingException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.SERVICE_UNAVAILABLE, "Cannot send email! Probably theres something wrong with the mail service! " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseMessage> handleIOException(IOException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.NOT_ACCEPTABLE, "Cannot send email with attachement! " + ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}
