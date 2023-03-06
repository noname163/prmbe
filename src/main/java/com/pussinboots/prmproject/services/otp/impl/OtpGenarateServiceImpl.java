package com.pussinboots.prmproject.services.otp.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.services.otp.OtpGenarateService;

import lombok.Builder;


@Service
@Builder
public class OtpGenarateServiceImpl implements OtpGenarateService {

    @Override
    public String genarateOtp() {
        Random random = new Random();
        int otpLength = 6;
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return sb.toString();
    }
    
}
