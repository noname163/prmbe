package com.pussinboots.prmproject.data.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Category;
import com.pussinboots.prmproject.data.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Optional<Product> findByName(String name);
    public Page<Product> findByCategoryId(Long id, Pageable pageable);
}
