package com.ecommerce.productservices.service;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;

public interface ProductService {
    Product addProduct(AddProductRequest addProductRequest);
}
