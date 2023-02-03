package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/product/category", method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        return new ResponseEntity(categoryService.addCategory(addCategoryRequest), HttpStatus.CREATED);
    }
}
