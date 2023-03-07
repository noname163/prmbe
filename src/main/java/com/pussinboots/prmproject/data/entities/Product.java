package com.pussinboots.prmproject.data.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Products")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_sequence")
    private long id;
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "image_url", unique = false, nullable = true, length = 1000)
    private String image;
    @Column(name = "model_year", unique = false, nullable = false)
    private Integer modelYear;
    @Column(name = "price", unique = false, nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    private Brand brand;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
    @OneToOne(fetch = FetchType.EAGER)
    private Stock stock;
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
