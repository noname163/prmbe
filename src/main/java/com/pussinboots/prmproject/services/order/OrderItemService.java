package com.pussinboots.prmproject.services.order;

import java.util.List;

import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.data.entities.Order;

public interface OrderItemService {
    public void createOrderItem(Order order, List<OrderItemRequest> orderItems);
}
