package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")

public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<>(productService.addProduct(addProductRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct() {
        return new ResponseEntity<>(productService.listProduct(),HttpStatus.OK);
    }
}
