package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dao.ProductDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import com.ecommerce.productservices.exception.ProductNotFoundException;
import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(addProductRequest.getCategoryId());
        CategoryDTO categoryDTO = CategoryServiceImpl.unwrapCategoryDTO(optionalCategoryDTO,addProductRequest.getCategoryId());
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




    @Override
    public List<Product> listProduct() {
        List<ProductDTO> productDTOList = productDAO.findAll();
        List<Product> productList = new ArrayList<>();
        productDTOList.forEach((productDTO) -> productList.add(new Product(productDTO.getId(),productDTO.getProductName()
                ,productDTO.getProductPrice()
                , productDTO.isActive()
                , productDTO.isAvailable()
                ,productDTO.getCreatedAt()
                ,productDTO.getCategoryDTO() )));

        return productList;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<ProductDTO> optionalProductDTO = productDAO.findById(id);
        return new Product();

    }
    static ProductDTO unwrapProductDTO(Optional<ProductDTO> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new ProductNotFoundException(id);
    }

}
