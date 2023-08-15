package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class EmailAttachmentMessage extends EmailMessage {

    // example: C://path//to//file.extensionname
    @NotBlank(message = "attachment cannot be blank, null, or empty!")
    private String absoluteAttachmentFileName;

    @Builder(builderMethodName = "emailAttachmentMessageBuilder")
    public EmailAttachmentMessage(String receiver, String subject, String messageBody, @NotNull(message = "attachment cannot be null!") String absoluteAttachmentFileName) {
        super(receiver, subject, messageBody);
        this.absoluteAttachmentFileName = absoluteAttachmentFileName;
    }
}
