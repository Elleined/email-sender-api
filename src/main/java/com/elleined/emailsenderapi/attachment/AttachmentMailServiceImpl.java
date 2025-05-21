package com.elleined.emailsenderapi.attachment;

import com.elleined.emailsenderapi.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class AttachmentMailServiceImpl implements AttachmentMailService {
    private final MailService mailService;

    @Override
    public void send(@Email @NotBlank String receiver,
                     @NotBlank String subject,
                     @NotBlank String message,
                     @NotNull MultipartFile attachment) throws MessagingException, IOException {

        mailService.send(receiver, subject, message, attachment);
        log.debug("Email with attachment sent successfully!");
    }
}
