package com.pussinboots.prmproject.services.order.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.OrderItem;
import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.data.repositories.OrderItemRepository;
import com.pussinboots.prmproject.data.repositories.OrderRepository;
import com.pussinboots.prmproject.data.repositories.ProductRepository;
import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.dto.response.OrderItemResponse;
import com.pussinboots.prmproject.exceptions.InsufficientStockException;
import com.pussinboots.prmproject.exceptions.NotFoundException;
import com.pussinboots.prmproject.exceptions.ProductNotFoundException;
import com.pussinboots.prmproject.services.order.OrderItemService;
import com.pussinboots.prmproject.mappers.OrderItemMapper;

import jakarta.transaction.Transactional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Transactional
    public void createOrderItem(Order order, List<OrderItemRequest> orderItemsRequest) {
        Map<Long, Integer> productIds = new HashMap<>();
        for (OrderItemRequest orderItem : orderItemsRequest) {
            long productId = orderItem.getProductId();
            int quantity = orderItem.getQuantity();
            productIds.merge(productId, quantity, Integer::sum);
        }

        List<Product> products = productRepository.findByIdIn(productIds.keySet());
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found with the given IDs");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (Product product : products) {
            long productId = product.getId();
            int quantityRequested = productIds.get(productId);
            int availableStock = product.getStock().getQuantity();
            if (quantityRequested > availableStock) {
                throw new InsufficientStockException("Insufficient stock for product " + productId);
            }
            orderItems.add(OrderItem.builder()
                    .product(product)
                    .order(order)
                    .quantity(quantityRequested)
                    .build());
        }
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public List<OrderItemResponse> getOrderItemResponse(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItemMapper.mapEntityToDto(orderItems);
    }

}
