package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class EmailAttachmentMessage extends EmailMessage {


    @Builder(builderMethodName = "attachmentMessageBuilder")
    public EmailAttachmentMessage(String receiver, String subject, String messageBody) {
        super(receiver, subject, messageBody);
    }
}
