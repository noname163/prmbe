package com.pussinboots.prmproject.mappers;

import org.springframework.stereotype.Component;

import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.OrderItem;
import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.dto.response.OrderItemResponse;

@Component
public class OrderItemMapper {
    public OrderItemResponse mapEntityToDto(OrderItem orderItem){
        Order order = orderItem.getOrder();
        Product product = orderItem.getProduct();
        return OrderItemResponse.builder()
        .discount(orderItem.getDiscount())
        .orderDate(order.getOrderDate())
        .shipDate(order.getShippedDate())
        .quantity(orderItem.getQuantity())
        .productImage(product.getImage())
        .productName(product.getName())
        .build();
    }
}
