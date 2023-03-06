package com.pussinboots.prmproject.services.productmanagement.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pussinboots.prmproject.data.entities.Product;
import com.pussinboots.prmproject.data.repositories.ProductRepository;
import com.pussinboots.prmproject.dto.request.PageableRequest;
import com.pussinboots.prmproject.dto.response.PaginationResponse;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.exceptions.NotFoundException;
import com.pussinboots.prmproject.mappers.ProductUserMapper;
import com.pussinboots.prmproject.utils.PageableUtil;

public class ProductServiceImplTest {
    private ProductRepository productRepository;
    private ProductUserMapper productUserMapper;
    private PageableUtil pageableUtil;
    private ProductServiceImpl productServiceImpl;
    private Product product;
    private ProductUserResponse productUserResponse;
    private PageableRequest pageableRequest;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productUserMapper = mock(ProductUserMapper.class);
        pageableUtil = mock(PageableUtil.class);
        productUserResponse = ProductUserResponse.builder()
                .name("test")
                .branch("test")
                .category("test")
                .modalYear(2023)
                .build();
        product = Product.builder()
                .name("test")
                .modelYear(2023)
                .build();
        productServiceImpl = ProductServiceImpl.builder()
                .pageableUtil(pageableUtil)
                .productRepository(productRepository)
                .productUserMapper(productUserMapper).build();
    }

    @Test
    void getProductById_WhenDataValid_ShouldReturnProductUserResponse() {

        when(productRepository.findById(1l)).thenReturn(Optional.of(product));
        when(productUserMapper.mapEntityToDto(product)).thenReturn(productUserResponse);
        ProductUserResponse actual = productServiceImpl.getProductById(1l);
        
        assertThat(actual, is(productUserResponse));
    }

    @Test
    void getProductById_WhenDataValid_ShouldReturnNotFoundException() {

        when(productRepository.findById(1l)).thenReturn(Optional.empty());
        when(productUserMapper.mapEntityToDto(product)).thenReturn(productUserResponse);
        NotFoundException actual = Assertions.assertThrows(NotFoundException.class, 
        ()-> productServiceImpl.getProductById(1l));

        assertThat(actual.getMessage(), is("Cannot found product with ID: 1"));
    }

    @Test
    void getProducts_WhenDataValid_ShouldReturnData() {
        List<ProductUserResponse> data = new ArrayList<>();
        List<Product> products = mock(List.class);
        Page<Product> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        data.add(productUserResponse);

        pageableRequest = PageableRequest.builder()
                .field("test")
                .size(1)
                .offSet(1)
                .sortType("ASC")
                .build();

        PaginationResponse expected = PaginationResponse.builder()
                .data(data)
                .totalPage(0)
                .totalRow(0).build();

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(pageableUtil.getPageable(pageableRequest.getOffSet(),
                pageableRequest.getSize(),
                pageableRequest.getField(),
                pageableRequest.getSortType())).thenReturn(pageable);
        when(productRepository.findAll(pageableCaptor.capture())).thenReturn(page);
        when(page.toList()).thenReturn(products);
        when(productUserMapper.mapEntityToDto(products)).thenReturn(data);

        PaginationResponse actual = productServiceImpl.getProducts(pageableRequest);

        assertThat(expected.getData(), is(actual.getData()));
        assertThat(expected.getTotalPage(), is(actual.getTotalPage()));
        assertThat(expected.getTotalRow(), is(actual.getTotalRow()));

    }
    @Test
    void getProducts_WhenDataEmpty_ShouldReturnNotFoundException() {
        Page<Product> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);

        pageableRequest = PageableRequest.builder()
                .field("test")
                .size(1)
                .offSet(1)
                .sortType("ASC")
                .build();


        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(pageableUtil.getPageable(pageableRequest.getOffSet(),
                pageableRequest.getSize(),
                pageableRequest.getField(),
                pageableRequest.getSortType())).thenReturn(pageable);
        when(productRepository.findAll(pageableCaptor.capture())).thenReturn(page);
        when(page.isEmpty()).thenReturn(true);

        NotFoundException actual = Assertions.assertThrows(NotFoundException.class, ()->
            productServiceImpl.getProducts(pageableRequest)
        );

        assertThat(actual.getMessage(), is("Product currenly is empty."));

    }
}
