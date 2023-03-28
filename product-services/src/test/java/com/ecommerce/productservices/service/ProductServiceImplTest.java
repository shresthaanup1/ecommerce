package com.ecommerce.productservices.service;

import com.ecommerce.productservices.dao.CategoryDAO;
import com.ecommerce.productservices.dao.ProductDAO;
import com.ecommerce.productservices.dto.CategoryDTO;
import com.ecommerce.productservices.dto.ProductDTO;
import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.model.Product;
import com.ecommerce.productservices.service.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    private ProductDAO productDAO;

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void addProductTest() {
        CategoryDTO categoryDTO = new CategoryDTO("TestCategory"
                ,"Test description");
        Optional<CategoryDTO> optionalCategoryDTO = Optional.of(categoryDTO);
        when(categoryDAO.findById(1L)).thenReturn(optionalCategoryDTO);

		AddProductRequest addProductRequest = new AddProductRequest("TestProduct"
									,10.0,true,true,null,1L);


		ProductDTO productDTO = new ProductDTO("TestProduct",10.0,true,true, LocalDateTime.now(),categoryDTO);
		//productDTO.setId(1L);
        productDTO.setId(1L);
		when(productDAO.save(any(ProductDTO.class))).thenReturn(productDTO);

		Product expectedProduct = new Product(1L, "TestProduct", 10.0, true, true, productDTO.getCreatedAt(), "TestCategory");
		Product actualProduct = productService.addProduct(addProductRequest);
		assertEquals(expectedProduct.getProductName(), actualProduct.getProductName());
		assertEquals(expectedProduct.getCategoryName(), actualProduct.getCategoryName());
        assertEquals(expectedProduct.getId(),actualProduct.getId());
    }

    @Test
    public void listProduct() {
    }

    @Test
    public void getProductById() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void updateProductByPatch() {
    }

    @Test
    public void deleteProduct() {
    }

    @Test
    public void getCategoryProducts() {
    }

    @Test
    public void unwrapProductDTO() {
    }
}
