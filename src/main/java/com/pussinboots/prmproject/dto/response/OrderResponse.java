package com.pussinboots.prmproject.dto.response;

import java.time.LocalDate;

import com.pussinboots.prmproject.data.constans.EOrderStatus;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.entities.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private EOrderStatus orderStatus;
    private String name;
    private String store;
    private String staff;
}
