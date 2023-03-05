package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.exception.CategoryNotFoundException;
import com.ecommerce.productservices.exception.JsonParameterNotValidException;
import com.ecommerce.productservices.exception.ProductNotFoundException;
import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
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
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public void deleteCategoryById(Long id) {

        Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(id);
        if(optionalCategoryDTO.isPresent()){
            categoryDAO.deleteById(id);
        }else{
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest updateCategoryRequest) {
       if (updateCategoryRequest.getId() != null) {
           Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(updateCategoryRequest.getId());
           if (optionalCategoryDTO.isPresent()) {
               CategoryDTO categoryDTO = optionalCategoryDTO.get();
               categoryDTO.setCategoryName(updateCategoryRequest.getCategoryName());
               categoryDTO.setCategoryDescription(updateCategoryRequest.getCategoryDescription());
               categoryDTO = categoryDAO.save(categoryDTO);
               Category category = new Category(categoryDTO.getId(),categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());
               return category;
           }
           else {
               throw new CategoryNotFoundException(updateCategoryRequest.getId());
           }
       } else {
           throw new JsonParameterNotValidException("id");
       }

    }

    @Override
    public Category updateCategoryByPatch(UpdateCategoryRequest updateCategoryRequest) {

        if(updateCategoryRequest.getId() != null){

            Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(updateCategoryRequest.getId());
            if (optionalCategoryDTO.isPresent()) {
                CategoryDTO categoryDTO = optionalCategoryDTO.get();

                if (updateCategoryRequest.getCategoryName() != null) {
                    categoryDTO.setCategoryName(updateCategoryRequest.getCategoryName());
                }

                if (updateCategoryRequest.getCategoryDescription() != null) {
                    categoryDTO.setCategoryDescription(updateCategoryRequest.getCategoryDescription());
                }

                categoryDTO = categoryDAO.save(categoryDTO);

                return new Category(categoryDTO.getId(), categoryDTO.getCategoryName(), categoryDTO.getCategoryDescription());

            } else {
                throw new CategoryNotFoundException(updateCategoryRequest.getId());
            }
        } else {
            throw new JsonParameterNotValidException("id");
        }
    }

    static CategoryDTO unwrapCategoryDTO(Optional<CategoryDTO> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CategoryNotFoundException(id);
    }
}
