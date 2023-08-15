package com.elleined.emailsender.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage extends Message {

    @NotBlank
    private String subject;

    @NotBlank
    private String messageBody;

    @Builder
    public EmailMessage(String receiver, String subject, String messageBody) {
        super(receiver);
        this.subject = subject;
        this.messageBody = messageBody;
    }
}
