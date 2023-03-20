package com.pussinboots.prmproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pussinboots.prmproject.services.order.OrderItemService;
import com.pussinboots.prmproject.services.order.OrderService;
import com.pussinboots.prmproject.dto.request.OrderAdminRequest;
import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.dto.request.OrderRequest;
import com.pussinboots.prmproject.dto.response.OrderItemResponse;
import com.pussinboots.prmproject.dto.response.OrderResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping()
    public ResponseEntity<Void> createOrder( @RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/admin")
    public ResponseEntity<Void> createOrderAdmin( @RequestBody OrderAdminRequest orderAdminRequest){
        orderService.createOrder(orderAdminRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(
            orderService.getOrder(email)
        );
    }
    @GetMapping("/order-items/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getOrderItems(@PathVariable Long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(
            orderItemService.getOrderItemResponse(orderId)
        );
    }
}
