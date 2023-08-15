package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailAttachmentMessage extends EmailMessage {

    @NotNull(message = "attachment cannot be null!")
    private MultipartFile attachment;


    @Builder(builderMethodName = "emailAttachmentMessageBuilder")
    public EmailAttachmentMessage(String receiver, String subject, String messageBody, MultipartFile attachment) {
        super(receiver, subject, messageBody);
        this.attachment = attachment;
    }
}
