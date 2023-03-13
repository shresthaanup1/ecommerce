package com.ecommerce.productservices.service;

import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.PatchProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest addProductRequest);
    List<Product> listProduct();
    Product getProductById(Long id);
    Product updateProduct(UpdateProductRequest updateProductRequest);
    Product updateProductByPatch(PatchProductRequest updateProductRequest);
    void deleteProduct(Long id);
    List<Product> getCategoryProducts(Long categoryId);

}
