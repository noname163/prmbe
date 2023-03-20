package com.pussinboots.prmproject.data.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Stores")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "store_sequence")
    private long id;

    @Column(name = "phone", nullable = false, unique = true, length = 13)
    private String phone;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "store_name", nullable = false, unique = false, length = 50)
    private String storeName;
    @Column(name = "street", nullable = false, unique = true, length = 30)
    private String street;
    @Column(name = "city", nullable = false, unique = true, length = 20)
    private String city;
    @Column(name = "state", nullable = false, unique = true, length = 20)
    private String state;
    @Column(name = "zip_code", nullable = false, unique = true)
    private Long zipCode;
    @OneToMany(mappedBy = "store")
    private List<Staff> staffs;
    @OneToOne(fetch = FetchType.LAZY)
    private Stock stock;
}
