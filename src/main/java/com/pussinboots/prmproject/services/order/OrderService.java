package com.pussinboots.prmproject.services.order;

import java.util.List;
import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.dto.request.OrderRequest;
import com.pussinboots.prmproject.dto.response.OrderResponse;

public interface OrderService {
    public void createOrder(OrderRequest orderRequest);
    public List<OrderResponse> getOrder(String email);
}
