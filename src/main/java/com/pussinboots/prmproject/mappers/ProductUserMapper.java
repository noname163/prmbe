package com.pussinboots.prmproject.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;

@Component
public class ProductUserMapper {

    public ProductUserResponse mapEntityToDto(Product product){
        return ProductUserResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .imageUrl(product.getImage())
        .modalYear(product.getModelYear())
        .price(product.getPrice())
        .branch(product.getBrand().getBrandName())
        .category(product.getCategory().getName())
        .build();
    }

    public List<ProductUserResponse> mapEntityToDto(List<Product> products){
        return products.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }
}
