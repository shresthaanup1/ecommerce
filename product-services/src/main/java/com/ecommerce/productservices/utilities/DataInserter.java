package com.ecommerce.productservices.utilities;

import com.ecommerce.productservices.model.AddCategoryRequest;
import com.ecommerce.productservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInserter {
    @Autowired
    CategoryService categoryService;
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

}
