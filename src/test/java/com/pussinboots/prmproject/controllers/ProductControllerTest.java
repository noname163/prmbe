package com.pussinboots.prmproject.controllers;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pussinboots.prmproject.dto.request.PageableRequest;
import com.pussinboots.prmproject.dto.response.PaginationResponse;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.exceptions.NotFoundException;
import com.pussinboots.prmproject.services.productmanagement.ProductService;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.services.authentication.*;
import com.pussinboots.prmproject.config.SecurityConfig;
import com.pussinboots.prmproject.filters.*;;

@WebMvcTest(value = ProductController.class)
@ContextConfiguration(classes = { ProductController.class, SecurityConfig.class })
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;
    @MockBean
    private SecurityContextService SecurityContextService;
    @MockBean
    private ExceptionHandlerFilter exceptionHandlerFilter;

    private PageableRequest pageableRequest;
    private ProductUserResponse productUserResponse;
    private NotFoundException notFoundException;

    @BeforeEach
    void setUp() {
        productUserResponse = ProductUserResponse.builder()
                .name("SamSung OLED 2023")
                .modalYear(2023)
                .category("TV")
                .branch("SamSung").build();
        pageableRequest = PageableRequest.builder()
                .field("test")
                .offSet(0)
                .size(1)
                .sortType("ASC").build();
        notFoundException = new NotFoundException("Error");
    }

    @Test
    void getProduct_WhenIdExist_ShouldReturnData() throws Exception{
        when(productService.getProductById(1l)).thenReturn(productUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/{id}", 1l);
        MockHttpServletResponse actual = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertThat(actual.getStatus(),is(HttpStatus.OK.value()));
        assertThat(actual.getContentAsString(), is("{\"id\":null,\"name\":\"SamSung OLED 2023\",\"imageUrl\":null,\"modalYear\":2023,\"price\":null,\"branch\":\"SamSung\",\"category\":\"TV\"}"));
    }
    // @Test
    // void getProduct_WhenIdNotExist_ShouldReturnNotFoundException() throws
    // Exception{
    // when(productService.getProductById(1l)).thenThrow(notFoundException);

    // RequestBuilder requestBuilder =
    // MockMvcRequestBuilders.get("/api/products/{id}", 1l);
    // MvcResult actual = mockMvc.perform(requestBuilder).andReturn();

    // assertThat(actual.getResponse().getStatus(),is(HttpStatus.NOT_FOUND.value()));
    // }

    @Test
    void getProducts_WhenDataValid_ShouldReturnData() throws Exception {
        List<ProductUserResponse> products = new ArrayList<>();
        products.add(productUserResponse);
        PaginationResponse<List<ProductUserResponse>> pagination = PaginationResponse
                .<List<ProductUserResponse>>builder().data(products).build();

        ArgumentCaptor<PageableRequest> pageableRequestCaptor = ArgumentCaptor.forClass(PageableRequest.class); 
        when(productService.getProducts(pageableRequestCaptor.capture())).thenReturn(pagination);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products")
                .content(objectMapper.writeValueAsString(pageableRequest))
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse actual = mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertThat(actual.getStatus(), is(HttpStatus.OK.value()));
        assertThat(actual.getContentAsString(), is("{\"data\":[{\"id\":null,\"name\":\"SamSung OLED 2023\",\"imageUrl\":null,\"modalYear\":2023,\"price\":null,\"branch\":\"SamSung\",\"category\":\"TV\"}],\"totalPage\":0,\"totalRow\":0}"));

    }
}
