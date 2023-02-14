package com.ecommerce.productservices.utilities;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.model.AddProductRequest;
import com.ecommerce.productservices.service.CategoryService;
import com.ecommerce.productservices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInserter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    public void insertCategories(){
        AddCategoryRequest[]  categories = new AddCategoryRequest[] {
                new AddCategoryRequest("Category1","Category1 Description"),
                new AddCategoryRequest("Category2","Category2 Description"),
                new AddCategoryRequest("Category3","Category3 Description"),
                new AddCategoryRequest("Category4","Category4 Description")
        };

        for(AddCategoryRequest addCategoryRequest:categories){
            categoryService.addCategory(addCategoryRequest);
        }
    }

    public void insertProducts(){
        AddProductRequest[]  products = new AddProductRequest[] {
                new AddProductRequest("PName1",100.0,true,true, LocalDateTime.now(),1L),
                new AddProductRequest("PName2",200.0,true,true, LocalDateTime.now(),2L),
                new AddProductRequest("PName3",300.0,true,true, LocalDateTime.now(),3L),
                new AddProductRequest("PName4",400.0,true,true, LocalDateTime.now(),4L),
        };

        for(AddProductRequest addProductRequest:products){
            productService.addProduct(addProductRequest);
        }
    }

}
