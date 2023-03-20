package com.pussinboots.prmproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.dto.request.CustomerRequest;
import com.pussinboots.prmproject.services.user.CustommerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustommerService custommerService;

    @PostMapping()
    public ResponseEntity<Void> createNewCustomer(@RequestBody CustomerRequest customerRequest){
        custommerService.createCustomer(customerRequest);
        return ResponseEntity.noContent().build();
    }

}
