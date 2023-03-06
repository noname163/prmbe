package com.pussinboots.prmproject.services.authentication.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.pussinboots.prmproject.config.TwilioConfig;
import com.pussinboots.prmproject.services.otp.OtpGenarateService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class TwilioServiceImplTest {

    private TwilioConfig twilioConfig;
    private OtpGenarateService otpGenarateService;
    private MessageCreator messageCreator;
    private TwilioServiceImpl twilioService;

    @BeforeEach
    void setUp() {
        twilioConfig = mock(TwilioConfig.class);
        otpGenarateService = mock(OtpGenarateService.class);
        messageCreator = mock(MessageCreator.class);
        twilioService = TwilioServiceImpl.builder()
                .twilioConfig(twilioConfig)
                .otpGenarateService(otpGenarateService)
                .build();
    }

    @Test
    public void sendSMS_shouldSendSMSSuccessfully() {
        // Arrange
        String phoneNumber = "1234567890";
        String otp = "1234";
        String message = "Your OTP use for login is: " + otp;
        Twilio twilio = mock(Twilio.class);
        ArgumentCaptor<PhoneNumber> systemPhoneCaptor = ArgumentCaptor.forClass(PhoneNumber.class);
        ArgumentCaptor<PhoneNumber> userPhoneCaptor = ArgumentCaptor.forClass(PhoneNumber.class);
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        ArgumentCaptor<MessageCreator> messageCreateCaptor = ArgumentCaptor.forClass(MessageCreator.class);
        Message result = mock(Message.class);
        when(twilioConfig.getAccountSid()).thenReturn("accountSid");
        when(twilioConfig.getAuthToken()).thenReturn("authToken");
        when(twilioConfig.getSystemPhoneNumber()).thenReturn("systemPhoneNumber");
        when(otpGenarateService.genarateOtp()).thenReturn(otp);
        doNothing().doAnswer(invocation -> {
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            return Twilio.getRestClient();
        }).when(twilio);
        when(messageCaptor.capture().creator(
                userPhoneCaptor.capture(),
                systemPhoneCaptor.capture(),
                eq(message))
                .create()).thenReturn(result);
        when(messageCreator.create()).thenReturn(result);
        // Act
        twilioService.sendSMS(phoneNumber);

        // Assert
        verify(twilioConfig).getAccountSid();
        verify(twilioConfig).getAuthToken();
        verify(twilioConfig).getSystemPhoneNumber();
        verify(otpGenarateService).genarateOtp();
        verify(Message.creator(any(PhoneNumber.class), any(PhoneNumber.class), eq(message))).create();
    }
}
