package com.pussinboots.prmproject.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderAdminRequest {
    private String staffEmail;
    private String customerEmail;
    private List<OrderItemRequest> orderItems;
}
