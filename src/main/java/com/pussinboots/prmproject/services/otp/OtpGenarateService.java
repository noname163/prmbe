package com.pussinboots.prmproject.services.otp;

import com.pussinboots.prmproject.dto.response.UserResponse;

public interface OtpGenarateService {
    public String genarateOtp(String email);
    public UserResponse validationOtp(String email, String otp);
}
