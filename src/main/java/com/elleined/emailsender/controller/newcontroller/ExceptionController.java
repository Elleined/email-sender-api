package com.elleined.emailsender.controller.newcontroller;

import com.elleined.emailsender.dto.ResponseMessage;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        var responseMessage = new ResponseMessage(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
