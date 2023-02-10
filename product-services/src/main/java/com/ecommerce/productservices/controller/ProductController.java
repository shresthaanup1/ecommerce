package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")

public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<>(productService.addProduct(addProductRequest), HttpStatus.CREATED);
    }
}
