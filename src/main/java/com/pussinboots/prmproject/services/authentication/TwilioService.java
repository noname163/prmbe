package com.pussinboots.prmproject.services.authentication;

public interface TwilioService {
    public void sendSMS(String phoneNumber);
    public String sendSMSString(String phoneNumber);
}
