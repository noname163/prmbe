package com.pussinboots.prmproject.data.entities;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Table(name ="Branchs")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @SequenceGenerator(name = "brand_sequence", sequenceName = "brand_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "brand_sequence")
    private long id;
    @Column(name ="brand_name", unique = true, nullable = false, length = 100)
    private String brandName;
    @OneToMany(mappedBy="brand")
    private List<Product> products;
}
