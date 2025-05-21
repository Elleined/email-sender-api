package com.elleined.emailsenderapi.attachment;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class AttachmentMailServiceImpl implements AttachmentMailService {
    private final JavaMailSender mailSender;

    @Value("${MAIL_USERNAME}")
    private String sender;

    @Override
    public void send(@Email @NotBlank String receiver,
                     @NotBlank String subject,
                     @NotBlank String message,
                     @NotNull MultipartFile attachment) throws MessagingException, IOException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);

        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), new ByteArrayResource(attachment.getBytes()));

        mailSender.send(mimeMessage);
        log.debug("Email with attachment sent successfully!");
    }
}
