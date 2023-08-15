package com.elleined.emailsender.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class OTPGeneratorService {
    private final Set<Integer> otpList = new HashSet<>();

    @Getter @Setter
    private LocalTime expiration;

    @Getter @Setter
    private String receiver;

    @Getter
    private Integer otp;

    public void generateOtp() {
        this.otp = new Random().nextInt(100_000, 999_999);
        if (otpList.contains(this.otp)) generateOtp();
        otpList.add(otp);
    }
}
