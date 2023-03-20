package com.pussinboots.prmproject.services.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.data.constans.ERole;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.dto.request.CustomerRequest;
import com.pussinboots.prmproject.exceptions.BadRequestException;
import com.pussinboots.prmproject.services.user.CustommerService;

@Service
public class CustomerServiceImpl implements CustommerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerRequest customerRequest) {

        Optional<Customer> customerOtp = customerRepository
                .findByEmail(customerRequest.getEmail());
        if (customerOtp.isPresent()) {
            throw new BadRequestException("User already exist.");
        }

        Customer customer = Customer.builder()
                .email(customerRequest.getEmail())
                .city(customerRequest.getCity())
                .firstName(customerRequest.getFirstname())
                .lastName(customerRequest.getLastname())
                .phone(customerRequest.getPhone())
                .role(ERole.USER)
                .build();

        customerRepository.save(customer);
    }

}
