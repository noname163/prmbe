package com.pussinboots.prmproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    
}
