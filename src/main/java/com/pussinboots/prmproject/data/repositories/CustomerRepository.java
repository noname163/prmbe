package com.pussinboots.prmproject.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByEmail(String email);
    public Optional<Customer> findByPhone(String email);
    public Optional<Customer> findByPhoneOrEmail(String phone, String email);
}
