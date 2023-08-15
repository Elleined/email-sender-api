package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.OTPMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OTPEmailService implements EmailService<OTPMessage> {
    @Override
    public void send(OTPMessage otpMessage) {

    }
}
