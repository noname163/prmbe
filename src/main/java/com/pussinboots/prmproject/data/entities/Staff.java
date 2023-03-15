package com.pussinboots.prmproject.data.entities;

import java.time.LocalDateTime;

import com.pussinboots.prmproject.data.constans.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Staffs")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "staff_sequence")
    private long id;
    @Column(name = "phone", nullable = false, unique = true, length = 13)
    private String phone;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "first_name", nullable = false, unique = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, unique = false, length = 20)
    private String lastName;
    @Column(name = "otp", nullable = true, unique = false, length = 20)
    private String otp;
    @Column(name = "create_token_date")
    private LocalDateTime createTokenDate;
    @Column(name = "active", nullable = false, unique = false, length = 20)
    private Boolean active;
    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "manage_id")
    private Staff manager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = false, length = 10)
    private ERole role;
}
