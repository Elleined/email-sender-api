package com.elleined.emailsenderapi.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Service
public class OTPGeneratorService {
    private final static Set<Integer> otpList = new HashSet<>();
    private final static SecureRandom secureRandom = new SecureRandom();
    private LocalTime expiration;
    private int otp;

    /**
     * This method sets the otp
     */
    public void generateOTP() {
        this.otp = secureRandom.nextInt(100_000, 999_999);
        if (OTPGeneratorService.otpList.contains(this.otp)) generateOTP();
        OTPGeneratorService.otpList.add(otp);
    }
}
