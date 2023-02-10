package com.ecommerce.productservices.service;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest addProductRequest);
    List<Product> listProduct();
    Product getProductById(Long id);
}
