package com.pussinboots.prmproject.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pussinboots.prmproject.data.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
