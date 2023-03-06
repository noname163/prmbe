package com.pussinboots.prmproject.services.category;

import java.util.List;
import com.pussinboots.prmproject.dto.response.CategoryResponse;

public interface CategoryService {
    public List<CategoryResponse> getCategories();
}
