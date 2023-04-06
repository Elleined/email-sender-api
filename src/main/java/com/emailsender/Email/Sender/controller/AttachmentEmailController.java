package com.emailsender.Email.Sender.controller;

import com.emailsender.Email.Sender.dto.EmailDTO;
import com.emailsender.Email.Sender.service.EmailService;
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
    private EmailService emailService;

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

        emailService.sendAttachmentMail(emailDTO);
        return "email-notification";
    }
}
