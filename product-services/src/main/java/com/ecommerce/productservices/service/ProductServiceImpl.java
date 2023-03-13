package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dao.ProductDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import com.ecommerce.productservices.exception.JsonParameterNotValidException;
import com.ecommerce.productservices.exception.ProductNotFoundException;
import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.PatchProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.model.UpdateProductRequest;
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
                ,productDTO.getCategoryDTO().getCategoryName());
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
                ,productDTO.getCategoryDTO().getCategoryName() )));

        return productList;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<ProductDTO> optionalProductDTO = productDAO.findById(id);
        ProductDTO productDTO = unwrapProductDTO(optionalProductDTO, id);
        return new Product(productDTO.getId(),productDTO.getProductName()
                ,productDTO.getProductPrice()
                ,productDTO.isActive()
                ,productDTO.isAvailable()
                ,productDTO.getCreatedAt()
                ,productDTO.getCategoryDTO().getCategoryName());

    }

    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest) {
        if (updateProductRequest.getId() != null) {
            Optional<ProductDTO> optionalProductDTO = productDAO.findById(updateProductRequest.getId());
            ProductDTO productDTO = unwrapProductDTO(optionalProductDTO, updateProductRequest.getId());
            Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(updateProductRequest.getCategoryId());
            CategoryDTO categoryDTO = CategoryServiceImpl.unwrapCategoryDTO(optionalCategoryDTO,updateProductRequest.getCategoryId());
            productDTO.setProductName(updateProductRequest.getProductName());
            productDTO.setProductPrice(updateProductRequest.getProductPrice());
            productDTO.setActive(updateProductRequest.getIsActive());
            productDTO.setAvailable(updateProductRequest.getIsAvailable());
            productDTO.setCreatedAt(LocalDateTime.now());
            productDTO.setCategoryDTO(categoryDTO);
            productDAO.save(productDTO);
            return new Product(productDTO.getId(), productDTO.getProductName()
                    ,productDTO.getProductPrice()
                    ,productDTO.isActive()
                    ,productDTO.isAvailable()
                    ,productDTO.getCreatedAt()
                    ,productDTO.getCategoryDTO().getCategoryName());
        }else{
            throw new JsonParameterNotValidException("id");
        }
    }

    @Override
    public Product updateProductByPatch(PatchProductRequest updateProductRequest) {
        if (updateProductRequest.getId() != null) {
            Optional<ProductDTO> optionalProductDTO = productDAO.findById(updateProductRequest.getId());
            ProductDTO productDTO = unwrapProductDTO(optionalProductDTO, updateProductRequest.getId());

            if (updateProductRequest.getProductName() != null) {
                productDTO.setProductName(updateProductRequest.getProductName());
            }
            if (updateProductRequest.getProductPrice() != null) {
                productDTO.setProductPrice(updateProductRequest.getProductPrice());
            }
            if (updateProductRequest.getIsActive() != null && updateProductRequest.getIsActive() != productDTO.isActive()) {
                productDTO.setActive(updateProductRequest.getIsActive());
            }
            if (updateProductRequest.getIsAvailable() != null && updateProductRequest.getIsAvailable() != productDTO.isAvailable()) {
                productDTO.setAvailable(updateProductRequest.getIsAvailable());
            }
            if (updateProductRequest.getCategoryId() != null) {
                Optional<CategoryDTO> optionalCategoryDTO = categoryDAO.findById(updateProductRequest.getCategoryId());
                CategoryDTO categoryDTO = CategoryServiceImpl.unwrapCategoryDTO(optionalCategoryDTO,updateProductRequest.getCategoryId());
                productDTO.setCategoryDTO(categoryDTO);
            }

            productDAO.save(productDTO);
            return new Product(productDTO.getId(), productDTO.getProductName()
                    ,productDTO.getProductPrice()
                    ,productDTO.isActive()
                    ,productDTO.isAvailable()
                    ,productDTO.getCreatedAt()
                    ,productDTO.getCategoryDTO().getCategoryName());
        }else{
            throw new JsonParameterNotValidException("id");
        }
    }


    @Override
    public void deleteProduct(Long id) {
        Optional<ProductDTO> optionalProductDTO = productDAO.findById(id);
        ProductDTO productDTO = unwrapProductDTO(optionalProductDTO, id);
        productDAO.deleteById(id);
    }
    @Override
    public List<Product> getCategoryProducts(Long categoryId) {
        List<ProductDTO> productDTOList = productDAO.findByCategoryId(categoryId);
        List<Product> productList = new ArrayList<>();
        productDTOList.forEach((productDTO) -> productList.add(new Product(productDTO.getId(),productDTO.getProductName()
                ,productDTO.getProductPrice()
                , productDTO.isActive()
                , productDTO.isAvailable()
                ,productDTO.getCreatedAt()
                ,productDTO.getCategoryDTO().getCategoryName() )));

        return productList;
    }


    static ProductDTO unwrapProductDTO(Optional<ProductDTO> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new ProductNotFoundException(id);
    }

}
