package com.ecommerce.productservices.service;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
     Category addCategory(AddCategoryRequest addCategoryRequest);
     List<Category> listCategories();
     Category getCategoryById(Long id);
     void deleteCategoryById(Long id);
     Category updateCategory(UpdateCategoryRequest updateCategoryRequest);
     Category updateCategoryByPatch(UpdateCategoryRequest updateCategoryRequest);
}
