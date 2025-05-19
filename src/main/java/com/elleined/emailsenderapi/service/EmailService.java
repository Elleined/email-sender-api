package com.elleined.emailsenderapi.service;

import com.elleined.emailsenderapi.request.MessageRequest;
import com.elleined.emailsenderapi.otp.OTPMessageRequest;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    void send(MessageRequest messageRequest) throws MessagingException;
    void send(OTPMessageRequest otpMessageRequest, String message) throws MessagingException;
    void send(MessageRequest messageRequest, MultipartFile attachment) throws MessagingException, IOException;

}
