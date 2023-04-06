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
@RequestMapping("/simple-email")
public class SimpleEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String goToSimpleEmailView(@ModelAttribute EmailDTO emailDTO) {
        return "simple-email";
    }

    @PostMapping("/sendSimpleMail")
    public String sendSimpleEmail(@Valid @ModelAttribute EmailDTO emailDTO,
                                  BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("Sending simple email has errors");
            return "simple-email";
        }

        emailService.sendSimpleMail(emailDTO);
        return "email-notification";
    }
}
