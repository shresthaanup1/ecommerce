package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    public Category addCategory(AddCategoryRequest addCategoryRequest) {
        CategoryDTO categoryDTO = new CategoryDTO(addCategoryRequest.getCategoryName(), addCategoryRequest.getCategoryDesc());
        categoryDTO = categoryDAO.save(categoryDTO);
        return new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());
    }
}
