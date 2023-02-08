package com.ecommerce.productservices.model;

import javax.validation.constraints.NotBlank;

public class AddCategoryRequest {
    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;
    @NotBlank(message = "Category Description cannot be blank")
    private String categoryDescription;

    public AddCategoryRequest() {

    }

    public AddCategoryRequest(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
