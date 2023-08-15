package com.elleined.emailsenderapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Message {

    @Email(message = "Please provide a proper email!")
    @NotBlank(message = "Receiver email cannot be blank, null, or empty!")
    private String receiver;
}
