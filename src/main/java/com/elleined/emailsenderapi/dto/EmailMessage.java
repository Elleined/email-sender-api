package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage extends Message {

    @NotBlank(message = "Email subject cannot be blank, null, or empty!")
    private String subject;

    @NotBlank(message = "Email Message cannot be blank, null, or empty!")
    private String messageBody;

    @Builder
    public EmailMessage(String receiver, String subject, String messageBody) {
        super(receiver);
        this.subject = subject;
        this.messageBody = messageBody;
    }
}
