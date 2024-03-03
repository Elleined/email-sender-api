package com.elleined.emailsenderapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmailAttachmentMessage extends EmailMessage {


    @Builder(builderMethodName = "attachmentMessageBuilder")
    public EmailAttachmentMessage(String receiver, String subject, String messageBody) {
        super(receiver, subject, messageBody);
    }
}
