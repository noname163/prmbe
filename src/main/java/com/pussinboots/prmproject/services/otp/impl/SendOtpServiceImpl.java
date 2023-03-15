package com.pussinboots.prmproject.services.otp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.services.otp.OtpGenarateService;
import com.pussinboots.prmproject.services.otp.SendOtpService;

@Service
public class SendOtpServiceImpl implements SendOtpService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OtpGenarateService otpGenarateService;
    
    @Override
    public void sendOtp(String to) {
        System.out.println("Send Otp");
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "Confirm OTP";
        String body = "Your OTP Code is " + otpGenarateService.genarateOtp(to)+ " will exprided in 60s";

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
    
}
