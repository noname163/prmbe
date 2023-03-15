package com.pussinboots.prmproject.mappers;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.OrderItem;
import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.dto.response.OrderItemResponse;

@Component
public class OrderItemMapper {
    public OrderItemResponse mapEntityToDto(OrderItem orderItem) {
        Order order = orderItem.getOrder();
        Product product = orderItem.getProduct();
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .discount(orderItem.getDiscount())
                .orderDate(order.getOrderDate() != null ? order.getOrderDate() : null)
                .shipDate(order.getShippedDate() != null ? order.getOrderDate() : null)
                .quantity(orderItem.getQuantity())
                .productImage(product.getImage())
                .productName(product.getName())
                .price(product.getPrice())
                .build();
    }

    public List<OrderItemResponse> mapEntityToDto(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
