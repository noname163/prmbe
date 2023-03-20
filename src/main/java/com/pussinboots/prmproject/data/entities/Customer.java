package com.pussinboots.prmproject.data.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.pussinboots.prmproject.data.constans.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Customers")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_sequence")
    private long id;
    @Column(name = "phone", nullable = false, unique = true, length = 13)
    private String phone;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "first_name", nullable = false, unique = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = true, unique = false, length = 20)
    private String lastName;
    @Column(name = "street", nullable = true, unique = false, length = 30)
    private String street;
    @Column(name = "city", nullable = true, unique = false, length = 20)
    private String city;
    @Column(name = "state", nullable = true, unique = false, length = 20)
    private String state;
    @Column(name = "otp", nullable = true, unique = true, length = 20)
    private String otp;
    @Column(name = "create_token_date")
    private LocalDateTime createTokenDate;
    @Column(name = "zip_code", nullable = true, unique = false)
    private Long zipCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = false, length = 10)
    private ERole role;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
