package com.pussinboots.prmproject.dto.request;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemRequest {
    private Integer quantity;
    private Long productId;
}
