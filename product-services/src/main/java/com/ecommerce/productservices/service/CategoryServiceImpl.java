package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Category addCategory(AddCategoryRequest addCategoryRequest) {
        CategoryDTO categoryDTO = new CategoryDTO(addCategoryRequest.getCategoryName(), addCategoryRequest.getCategoryDescription());
        categoryDTO = categoryDAO.save(categoryDTO);
        return new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());
    }

    @Override
    public List<Category> listCategories() {
        List<CategoryDTO> categoryDTOs = categoryDAO.findAll();
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOs) {
            categories.add(new Category(categoryDTO.getId(),categoryDTO.getCategoryName(),categoryDTO.getCategoryDescription()));
        }
        return categories;
    }
    @Override
    public Category getCategoryById(Long id) {
        Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(id);
        if (optionalCategoryDTO.isPresent()) {
            CategoryDTO categoryDTO = optionalCategoryDTO.get();
            Category category = new Category(categoryDTO.getId(),categoryDTO.getCategoryName(),categoryDTO.getCategoryDescription());
            return category;
        } else {
            return new Category();
        }
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryDAO.deleteById(id);
    }

}
