package com.elleined.emailsenderapi.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@Service
public class OTPGeneratorService {
    private final Set<Integer> otpList = new HashSet<>();
    private LocalTime expiration;
    private int otp;

    /**
     * This method sets the otp
     */
    public void generateOTP() {
        this.otp = new Random().nextInt(100_000, 999_999);
        if (otpList.contains(this.otp)) generateOTP();
        otpList.add(otp);
    }
}
