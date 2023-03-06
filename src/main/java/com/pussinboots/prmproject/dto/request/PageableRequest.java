package com.pussinboots.prmproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableRequest {
    @NotBlank(message = "You must provide current page")
    private int offSet;
    @NotBlank(message = "You must provide number of item")
    private int size;
    private String field;
    private String sortType;
}
