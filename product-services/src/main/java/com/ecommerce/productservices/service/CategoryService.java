package com.ecommerce.productservices.service;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;

import java.util.List;

public interface CategoryService {
     Category addCategory(AddCategoryRequest addCategoryRequest);
     List<Category> listCategories();
     Category getCategoryById(Long id);
}
