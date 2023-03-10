package com.pussinboots.prmproject.data.entities;

import java.time.LocalDate;
import java.util.List;


import com.pussinboots.prmproject.data.constans.EOrderStatus;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Orders")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
    private long id;
    @Column(name = "Order_date", unique = false, nullable = false, length = 100)
    private LocalDate orderDate;
    @Column(name = "Require_date", unique = false, nullable = true, length = 100)
    private LocalDate requiredDate;
    @Column(name = "Shipped_date", unique = false, nullable = true, length = 100)
    private LocalDate shippedDate;
    @Column(name = "Order_status", unique = false, nullable = false, length = 100)
    private EOrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;
}
