package com.pussinboots.prmproject.services.order.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.data.constans.EOrderStatus;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Order;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.entities.User;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.data.repositories.OrderRepository;
import com.pussinboots.prmproject.data.repositories.StaffRepository;
import com.pussinboots.prmproject.dto.request.OrderAdminRequest;
import com.pussinboots.prmproject.dto.request.OrderItemRequest;
import com.pussinboots.prmproject.dto.request.OrderRequest;
import com.pussinboots.prmproject.dto.response.OrderResponse;
import com.pussinboots.prmproject.exceptions.NotFoundException;
import com.pussinboots.prmproject.services.order.OrderItemService;
import com.pussinboots.prmproject.services.order.OrderService;
import com.pussinboots.prmproject.mappers.OrderMapper;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private OrderMapper orderMapper; 

    @Transactional
    @Override
    public void createOrder(OrderRequest orderRequest) {

        String email = orderRequest.getEmail();
        Optional<Customer> customerOtp = customerRepository.findByEmail(email);
        List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItems();
        
        if (customerOtp.isEmpty()) {
            throw new NotFoundException("Cannot found user with phone " + email);
        }

        Customer customer = customerOtp.get();

        Order order = Order.builder().customer(customer)
                .orderDate(LocalDate.now())
                .orderStatus(EOrderStatus.PENDING)
                .build();
        order = orderRepository.save(order);
        orderItemService.createOrderItem(order, orderItemRequests);
    }

    @Transactional
    @Override
    public void createOrder(OrderAdminRequest orderAdminRequest) {

        String customerEmail = orderAdminRequest.getCustomerEmail();
        String staffEmail = orderAdminRequest.getStaffEmail();
        Optional<Customer> customerOtp = customerRepository.findByEmail(customerEmail);
        
        if (customerOtp.isEmpty()) {
            throw new NotFoundException("Cannot found user with email " + customerEmail);
        }
        Optional<Staff> staffOtp = staffRepository.findByEmail(staffEmail);
        if(staffOtp.isEmpty()){
            throw new NotFoundException("Cannot found user with email " + staffEmail);
        }

        List<OrderItemRequest> orderItemRequests = orderAdminRequest.getOrderItems();
        Customer customer = customerOtp.get();
        Staff staff = staffOtp.get();

        Order order = Order.builder().customer(customer)
                .orderDate(LocalDate.now())
                .orderStatus(EOrderStatus.PENDING)
                .staff(staff)
                .store(staff.getStore())
                .build();
        order = orderRepository.save(order);
        orderItemService.createOrderItem(order, orderItemRequests);
    }

    @Override
    public List<OrderResponse> getOrder(String email) {
        Optional<Customer> customerOtp = customerRepository.findByEmail(email);
        if(customerOtp.isEmpty()){
            throw new NotFoundException("Cannot found user with phone " + email);
        }
        Customer customer = customerOtp.get();
        List<Order> orders = orderRepository.findByCustomerId(customer.getId());
        return orderMapper.mapEntityToDto(orders);
    }
}
