package com.ecommerce.productservices.model;

import com.ecommerce.productservices.validation.IsPresentAndValid;

import javax.validation.constraints.NotBlank;

public class UpdateCategoryRequest {
    private Long id;

    @IsPresentAndValid(message="Category name should not be blank.")
    private String categoryName;

    @IsPresentAndValid(message="Category description should not be blank.")
    private String categoryDescription;

    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(Long id, String categoryName, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
