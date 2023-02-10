package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dao.ProductDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        CategoryDTO categoryDTO = categoryDAO.findById(addProductRequest.getCategoryId()).get();
        ProductDTO productDTO = new ProductDTO(addProductRequest.getProductName()
                ,addProductRequest.getProductPrice()
                , addProductRequest.isActive()
                , addProductRequest.isAvailable()
                , LocalDateTime.now()
                , categoryDTO);
        productDAO.save(productDTO);
        return new Product(productDTO.getId()
                ,productDTO.getProductName()
                ,productDTO.getProductPrice()
                , productDTO.isActive()
                , productDTO.isAvailable()
                ,productDTO.getCreatedAt()
                ,productDTO.getCategoryDTO());
    }
}
