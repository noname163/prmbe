package com.pussinboots.prmproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private String message;
}
