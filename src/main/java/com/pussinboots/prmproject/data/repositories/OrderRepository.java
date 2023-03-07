package com.pussinboots.prmproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
