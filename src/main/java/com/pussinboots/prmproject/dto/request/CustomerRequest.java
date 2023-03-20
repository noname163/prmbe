package com.pussinboots.prmproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest{
    private String email;
    private String phone;
    private String address;
    private String city;
    private String firstname;
    private String lastname;
}