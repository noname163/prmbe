package com.pussinboots.prmproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pussinboots.prmproject.services.authentication.TwilioService;

@RestController
@RequestMapping("/api/authen")
public class AuthenController {
    @Autowired
    private TwilioService twilioService;

    @GetMapping()
    public ResponseEntity<String> sendOtpService(@RequestParam String phone){
        return ResponseEntity.ok().body(
            twilioService.sendSMSString(phone)
        );
    }

}
