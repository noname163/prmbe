package com.pussinboots.prmproject.mappers;

import org.springframework.stereotype.Component;

import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.dto.response.OrderResponse;

@Component
public class OrderMapper {
    public OrderResponse mapEntityToDto(Order order){
        Customer customer = order.getCustomer();
        Staff staff = order.getStaff();
        return OrderResponse.builder()
        .name(customer.getLastName() + " " + customer.getFirstName())
        .orderDate(order.getOrderDate())
        .orderStatus(order.getOrderStatus())
        .requiredDate(order.getRequiredDate())
        .shippedDate(order.getShippedDate())
        .staff(staff.getLastName() + " " + staff.getFirstName())
        .store(order.getStore().getStoreName())
        .build();
    }
}
