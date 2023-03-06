package com.pussinboots.prmproject.services.category.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.services.category.CategoryService;
import com.pussinboots.prmproject.dto.response.CategoryResponse;
import com.pussinboots.prmproject.data.repositories.CategoryRepository;
import com.pussinboots.prmproject.data.entities.Category;
import com.pussinboots.prmproject.mappers.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper; 

    @Override
    public List<CategoryResponse> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mapEntityToDto(categories);
    }
}
