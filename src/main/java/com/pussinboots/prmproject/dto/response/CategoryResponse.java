package com.pussinboots.prmproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private String image;
}
