package com.pussinboots.prmproject.customargumentmatcher;

import org.mockito.ArgumentMatcher;

import com.twilio.type.PhoneNumber;

public class PhoneNumberMatcher implements ArgumentMatcher<PhoneNumber> {
    
    private final String expectedPhoneNumber;
    
    public PhoneNumberMatcher(String expectedPhoneNumber) {
        this.expectedPhoneNumber = expectedPhoneNumber;
    }
    
    @Override
    public boolean matches(PhoneNumber phoneNumber) {
        return phoneNumber != null && expectedPhoneNumber.equals(phoneNumber.getEndpoint());
    }
}
