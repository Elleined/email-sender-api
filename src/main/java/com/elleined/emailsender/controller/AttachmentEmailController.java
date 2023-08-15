package com.elleined.emailsender.controller;

import com.elleined.emailsender.dto.EmailDTO;
import com.elleined.emailsender.service.EmailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attachment-email")
public class AttachmentEmailController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @GetMapping
    public String goToAttachmentEmailView(@ModelAttribute EmailDTO emailDTO) {
        return "email-attachment";
    }

    @PostMapping("/sendAttachmentMail")
    public String sendEmailWithAttachment(@Valid @ModelAttribute EmailDTO emailDTO,
                                          BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Sending email with attachment has errors!");
            return "email-attachment";
        }

        emailServiceImpl.sendAttachmentMail(emailDTO);
        return "email-notification";
    }
}
