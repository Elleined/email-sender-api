package com.elleined.emailsender.service;

import com.elleined.emailsender.dto.EmailAttachmentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttachmentEmailService implements EmailService<EmailAttachmentMessage> {
    @Override
    public void send(EmailAttachmentMessage emailAttachmentMessage) {

    }
}
