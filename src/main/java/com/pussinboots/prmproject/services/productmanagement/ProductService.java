package com.pussinboots.prmproject.services.productmanagement;

import java.util.List;

import com.pussinboots.prmproject.dto.response.PaginationResponse;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.dto.request.PageableRequest;

public interface ProductService {
    public ProductUserResponse getProductById(Long id);

    public PaginationResponse<List<ProductUserResponse>> getProducts(PageableRequest pageableRequest);

    public PaginationResponse<List<ProductUserResponse>> gettProductsByCate(Long id, PageableRequest pageableRequest);
}
