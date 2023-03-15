package com.pussinboots.prmproject.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderRequest {
    private String email;
    private List<OrderItemRequest> orderItems;
}
