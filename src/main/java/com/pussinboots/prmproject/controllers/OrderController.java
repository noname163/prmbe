package com.pussinboots.prmproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pussinboots.prmproject.services.order.OrderService;
import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.dto.request.OrderRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<Void> createOrder( @RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseEntity.noContent().build();
    }

}
