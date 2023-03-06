package com.pussinboots.prmproject.services.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.config.TwilioConfig;
import com.pussinboots.prmproject.services.authentication.TwilioService;
import com.pussinboots.prmproject.services.otp.OtpGenarateService;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.Builder;

@Service
@Builder
public class TwilioServiceImpl implements TwilioService {

    @Autowired
    private TwilioConfig twilioConfig;
    @Autowired
    private OtpGenarateService otpGenarateService;

    @Override
    public void sendSMS(String phoneNumber) {
        String message = "Your OTP use for login is: " + otpGenarateService.genarateOtp();
        twilioConfig.twilioConnect();
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfig.getSystemPhoneNumber()),
                message).create();
    }

    @Override
    public String sendSMSString(String phoneNumber) {
        try {
            String message = "Your OTP use for login is: " + otpGenarateService.genarateOtp();
            twilioConfig.twilioConnect();
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioConfig.getSystemPhoneNumber()),
                    message).create();
            return message;
        } catch (TwilioException e) {
                System.out.println("Error: " + e.getMessage());
            return e.getMessage();
        }
    }

}
