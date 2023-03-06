package com.pussinboots.prmproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pussinboots.prmproject.services.category.CategoryService;
import com.pussinboots.prmproject.dto.response.CategoryResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getCategories(){
        System.out.println("Get category");
        return ResponseEntity.status(HttpStatus.OK).body(
            categoryService.getCategories()
        );
    }
    
}
