package com.elleined.emailsenderapi;

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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Value("${MAIL_USERNAME}")
    private String sender;

    // https://mailtrap.io/blog/java-email-validation/
    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Async
    @Override
    public void send(@NotBlank @Email String receiver,
                     @NotBlank String subject,
                     @NotBlank String message) throws MessagingException {

        if (!Pattern.compile(regex)
                .matcher(receiver)
                .matches())
            throw new MessagingException("Invalid email address");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);

        mailSender.send(mimeMessage);
    }

    @Async
    @Override
    public void send(@Email @NotBlank String receiver,
                     @NotBlank String subject,
                     @NotBlank String message,
                     @NotNull MultipartFile attachment) throws MessagingException, IOException {

        if (!Pattern.compile(receiver)
                .matcher(receiver)
                .matches())
            throw new MessagingException("Invalid email address");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), new ByteArrayResource(attachment.getBytes()));

        mailSender.send(mimeMessage);
    }
}
