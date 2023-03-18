package com.pussinboots.prmproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pussinboots.prmproject.dto.response.UserResponse;
import com.pussinboots.prmproject.services.authentication.TwilioService;
import com.pussinboots.prmproject.services.otp.OtpGenarateService;
import com.pussinboots.prmproject.services.otp.SendOtpService;

@RestController
@RequestMapping("/api/authen")
public class AuthenController {
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private SendOtpService sendOtpService;
    @Autowired
    private OtpGenarateService otpGenarateService;

    @GetMapping("/{email}")
    public ResponseEntity<Void> sendOtpService(@PathVariable String email){
        sendOtpService.sendOtp(email);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/validation/{email}/{otp}")
    public ResponseEntity<UserResponse> validationOtp(@PathVariable String email, @PathVariable String otp){
        return ResponseEntity.ok().body(
            otpGenarateService.validationOtp(email, otp)
        );
    }

}
