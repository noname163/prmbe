package com.pussinboots.prmproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.twilio.Twilio;

import lombok.Getter;

@Configuration
@Getter
public class TwilioConfig {
    @Value("${twilio.accountSid}")
    private String accountSid;
    @Value("${twilio.authToken}")
    private String authToken;
    @Value("${twilio.phoneNumber}")
    private String systemPhoneNumber;

    public void twilioConnect(){
        Twilio.init(accountSid, authToken);
    }
}
