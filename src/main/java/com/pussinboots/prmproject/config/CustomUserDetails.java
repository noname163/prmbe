package com.pussinboots.prmproject.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pussinboots.prmproject.data.constans.ERole;
import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;
    private ERole role;
    private final transient Staff staff;
    private final transient Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUserDetails build(Staff staff) {
        return CustomUserDetails.builder()
                .email(staff.getEmail())
                .role(staff.getRole())
                .build();
    }

    public static CustomUserDetails build(Customer customer) {
        return CustomUserDetails.builder()
                .email(customer.getEmail())
                .role(customer.getRole())
                .build();
    }

    public Staff getStaff() {
        return this.staff;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}
