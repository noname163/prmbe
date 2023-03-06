package com.pussinboots.prmproject.data.entities;


import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Table(name = "Stocks")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @SequenceGenerator(name = "stock_sequence", sequenceName = "stock_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "stock_sequence")
    private long id;
    @Column(name = "quantity", unique = false, nullable = true)
    private int quantity;
    @OneToOne(mappedBy = "stock")
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne(mappedBy = "stock")
    @JoinColumn(name = "store_id")
    private Store store;
}
