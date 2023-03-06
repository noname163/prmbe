package com.pussinboots.prmproject.dto.response;

import com.pussinboots.prmproject.data.entities.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductUserResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Integer modalYear;
    private Double price;
    private String branch;
    private String category;
}
