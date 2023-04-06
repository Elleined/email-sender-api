package com.emailsender.Email.Sender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailDTO {

    @Email
    private String receiver;

    @NotBlank
    private String subject;


    @NotBlank
    private String messageBody;

    private MultipartFile attachment;
}
