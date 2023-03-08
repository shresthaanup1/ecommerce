package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.Category;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
import com.ecommerce.productservices.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/product")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    //@RequestMapping(value = "/category", method = RequestMethod.POST)
    @PostMapping(value = "/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest) {
        return new ResponseEntity(categoryService.addCategory(addCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> listCategories() {
        return new ResponseEntity<>(categoryService.listCategories(), HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        LOGGER.info("CategoryId:" + id);
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/category")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(updateCategoryRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/category")
    public ResponseEntity<Category> updateCategoryByPatch(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return new ResponseEntity<>(categoryService.updateCategoryByPatch(updateCategoryRequest), HttpStatus.OK);
    }

}
