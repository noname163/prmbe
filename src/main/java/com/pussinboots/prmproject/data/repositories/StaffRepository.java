package com.pussinboots.prmproject.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pussinboots.prmproject.data.entities.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public Optional<Staff> findByEmail(String email);
    public Optional<Staff> findByPhone(String phone);
    public Optional<Staff> findByPhoneOrEmail(String phone, String email);
}
