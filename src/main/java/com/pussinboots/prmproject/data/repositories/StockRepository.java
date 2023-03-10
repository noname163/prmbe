package com.pussinboots.prmproject.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    
}
