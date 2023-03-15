package com.pussinboots.prmproject.services.otp;

public interface OtpGenarateService {
    public String genarateOtp(String email);
    public Boolean validationOtp(String email, String otp);
}
