package com.elleined.emailsenderapi.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MessageRequest {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;

    @NotBlank(message = "Email subject cannot be blank, null, or empty!")
    private String subject;

    @NotBlank(message = "Email Message cannot be blank, null, or empty!")
    private String messageBody;
}
