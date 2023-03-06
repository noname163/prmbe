package com.pussinboots.prmproject.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pussinboots.prmproject.data.entities.Category;
import com.pussinboots.prmproject.dto.response.CategoryResponse;

@Component
public class CategoryMapper {
    public CategoryResponse mapEntityToDto(Category category){
        return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .image(category.getImage())
        .build();
    }

    public List<CategoryResponse> mapEntityToDto(List<Category> category){
        return category.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }
}
