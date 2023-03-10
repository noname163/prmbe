package com.pussinboots.prmproject.mappers;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.dto.response.OrderResponse;

@Component
public class OrderMapper {
    public OrderResponse mapEntityToDto(Order order) {
        Customer customer = order.getCustomer();
        Staff staff = order.getStaff();
        return OrderResponse.builder()
                .id(order.getId())
                .name(customer != null ? customer.getLastName() + " " + customer.getFirstName() : null)
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .requiredDate(order.getRequiredDate())
                .shippedDate(order.getShippedDate())
                .staff(staff != null ? staff.getLastName() + " " + staff.getFirstName() : null)
                .store(order.getStore() != null ? order.getStore().getStoreName() : null)
                .build();
    }

    public List<OrderResponse> mapEntityToDto(List<Order> orders) {
        return orders.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
