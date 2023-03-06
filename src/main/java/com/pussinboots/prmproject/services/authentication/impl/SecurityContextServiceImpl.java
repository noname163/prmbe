package com.pussinboots.prmproject.services.authentication.impl;

import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.config.CustomUserDetails;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.entities.User;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.data.repositories.StaffRepository;
import com.pussinboots.prmproject.data.repositories.UserRepositories;
import com.pussinboots.prmproject.exceptions.ForbiddenException;
import com.pussinboots.prmproject.services.authentication.SecurityContextService;
import com.pussinboots.prmproject.services.user.UserDetailsService;

import jakarta.transaction.Transactional;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@Transactional
public class SecurityContextServiceImpl implements SecurityContextService {

    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void setSecurityContext(String username) {
        Optional<Customer> customer = customerRepository.findByPhoneOrEmail(username,username);
        Optional<Staff> staff = staffRepository.findByPhoneOrEmail(username,username);
        if (customer.isEmpty() && staff.isEmpty()) {
            throw new ForbiddenException("Invalid username in JWT.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);

    }

    @Override
    public Staff getCurrentStaff() {
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        return ((CustomUserDetails) principal).getStaff();
    }

    @Override
    public Customer getCurrentCustomer() {
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        return ((CustomUserDetails) principal).getCustomer();
    }

}
