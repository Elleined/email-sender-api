package com.emailsender.Email.Sender.controller;

import com.emailsender.Email.Sender.dto.EmailOTPDto;
import com.emailsender.Email.Sender.service.EmailService;
import com.emailsender.Email.Sender.service.OTPGeneratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequestMapping("/email-otp")
public class OTPEmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPGeneratorService otpGeneratorService;

    @GetMapping
    public String goToOTPEmailView(@ModelAttribute EmailOTPDto emailOTPDto) {
        return "email-otp";
    }

    @PostMapping("/sendOTP")
    public String sendOTP(@RequestParam String receiver,
                          Model model) {

        otpGeneratorService.generateOtp();
        otpGeneratorService.setExpiration(LocalTime.now().plusSeconds(60));

        EmailOTPDto emailOTPDto = EmailOTPDto.builder()
                .receiver(receiver)
                .OTP(otpGeneratorService.getOtp())
                .expiration(otpGeneratorService.getExpiration())
                .build();
        System.out.println(emailOTPDto);

        model.addAttribute("emailOTPDto", emailOTPDto);
        emailService.sendOTPMail(emailOTPDto);
        return "email-otp-enter";
    }

    @PostMapping("/sendOTP/confirmOTP")
    public String confirmOTP(@Valid @ModelAttribute EmailOTPDto emailOTPDto,
                             BindingResult result) {

        if (result.hasErrors()) return "email-otp-enter";
        return "email-notification";
    }
}
