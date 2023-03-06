package com.pussinboots.prmproject.services.user.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.config.CustomUserDetails;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.data.repositories.StaffRepository;
import com.pussinboots.prmproject.services.user.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(StaffRepository staffRepository, CustomerRepository customerRepository) {
        this.staffRepository = staffRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find user by email
        Optional<Staff> staffOptional = staffRepository.findByEmail(username);
        Optional<Customer> customerOptional = customerRepository.findByEmail(username);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            return CustomUserDetails.build(staff);
        } else if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return CustomUserDetails.build(customer);
        }

        // If not found by email, try to find by phone
        staffOptional = staffRepository.findByPhone(username);
        customerOptional = customerRepository.findByPhone(username);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            return CustomUserDetails.build(staff);
        } else if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return CustomUserDetails.build(customer);
        }

        // If user not found, throw exception
        throw new UsernameNotFoundException("User not found with email/phone: " + username);
    }
}

