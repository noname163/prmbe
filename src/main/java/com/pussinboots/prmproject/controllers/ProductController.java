package com.pussinboots.prmproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pussinboots.prmproject.dto.request.PageableRequest;
import com.pussinboots.prmproject.dto.response.PaginationResponse;
import com.pussinboots.prmproject.dto.response.ProductUserResponse;
import com.pussinboots.prmproject.services.productmanagement.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductUserResponse> getProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
            productService.getProductById(id)
        );
    }

    @PostMapping()
    public ResponseEntity<PaginationResponse<List<ProductUserResponse>>> getProducts(@RequestBody PageableRequest pageable){
        System.out.println("Get in products");
        return ResponseEntity.status(HttpStatus.OK).body(
            productService.getProducts(pageable)
        );
    }
    @PostMapping("/by-category/{id}")
    public ResponseEntity<PaginationResponse<List<ProductUserResponse>>> getProductsByCategory(@PathVariable Long id,@RequestBody PageableRequest pageable){
        System.out.println("Get in products");
        return ResponseEntity.status(HttpStatus.OK).body(
            productService.gettProductsByCate(id, pageable)
        );
    }
}
