package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateCategoryRequest;
import com.ecommerce.productservices.model.UpdateProductRequest;
import com.ecommerce.productservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")

public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<>(productService.addProduct(addProductRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct() {
        return new ResponseEntity<>(productService.listProduct(),HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(productService.updateProduct(updateProductRequest), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProductByPatch(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(productService.updateProductByPatch(updateProductRequest), HttpStatus.CREATED);
    }
    @DeleteMapping(value ="/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable Long categoryId) {
        return new ResponseEntity<>(productService.getCategoryProducts(categoryId), HttpStatus.OK);
    }

}
