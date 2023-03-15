package com.pussinboots.prmproject.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class OrderItemResponse {
    private Long id;
    private Integer quantity;
    private Double discount;
    private LocalDate orderDate;
    private LocalDate shipDate;
    private String productName;
    private Double price;
    private String productImage;
}
