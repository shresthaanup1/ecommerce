package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
import com.ecommerce.productservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //@RequestMapping(value = "/category", method = RequestMethod.POST)
    @PostMapping(value = "/category")
    public ResponseEntity<Category> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        return new ResponseEntity(categoryService.addCategory(addCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> listCategories() {
        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/category")
    public ResponseEntity<Category> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(updateCategoryRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/category")
    public ResponseEntity<Category> updateCategoryByPatch(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategoryByPatch(updateCategoryRequest), HttpStatus.OK);
    }

}
