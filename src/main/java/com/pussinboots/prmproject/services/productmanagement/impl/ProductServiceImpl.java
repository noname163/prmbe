package com.pussinboots.prmproject.services.productmanagement.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.data.repositories.ProductRepository;
import com.pussinboots.prmproject.dto.request.PageableRequest;
import com.pussinboots.prmproject.dto.response.PaginationResponse;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.exceptions.NotFoundException;
import com.pussinboots.prmproject.mappers.ProductUserMapper;
import com.pussinboots.prmproject.services.productmanagement.ProductService;
import com.pussinboots.prmproject.utils.PageableUtil;

import lombok.Builder;

@Service
@Builder
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductUserMapper productUserMapper;
    @Autowired
    private PageableUtil pageableUtil;

    @Override
    public ProductUserResponse getProductById(Long id) {
        Optional<Product> productOtp = productRepository.findById(id);
        if (productOtp.isEmpty()) {
            throw new NotFoundException("Cannot found product with ID: " + id);
        }
        return productUserMapper.mapEntityToDto(productOtp.get());

    }

    @Override
    public PaginationResponse<List<ProductUserResponse>> getProducts(PageableRequest pageableRequest) {

        Pageable pageable = pageableUtil.getPageable(pageableRequest.getOffSet(),
                pageableRequest.getSize(),
                pageableRequest.getField(),
                pageableRequest.getSortType());
        Page<Product> productsPage = productRepository.findAll(pageable);

        if (productsPage.isEmpty()) {
            throw new NotFoundException("Product currenly is empty.");
        }

        List<ProductUserResponse> products = productUserMapper.mapEntityToDto(productsPage.toList());
        return PaginationResponse.<List<ProductUserResponse>>builder()
                .data(products)
                .totalRow(productsPage.getTotalElements())
                .totalPage(productsPage.getTotalPages())
                .build();
    }

    @Override
    public PaginationResponse<List<ProductUserResponse>> gettProductsByCate(Long id, PageableRequest pageableRequest) {
        Pageable pageable = pageableUtil.getPageable(pageableRequest.getOffSet(),
                pageableRequest.getSize(),
                pageableRequest.getField(),
                pageableRequest.getSortType());
        Page<Product> productsPage = productRepository.findByCategoryId(id, pageable);

        List<ProductUserResponse> products = productUserMapper.mapEntityToDto(productsPage.toList());
        return PaginationResponse.<List<ProductUserResponse>>builder()
                .data(products)
                .totalRow(productsPage.getTotalElements())
                .totalPage(productsPage.getTotalPages())
                .build();
    }

}
