package com.pussinboots.prmproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private String email;
    private Boolean isAdmin;
}
