package com.elleined.emailsender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailDTO {

    @Email
    @NotBlank
    private String receiver;

    @NotBlank
    private String subject;

    @NotBlank
    private String messageBody;

    private MultipartFile attachment;
}
