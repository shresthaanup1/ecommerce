package com.ecommerce.productservices.model;

public class AddCategoryRequest {
    private String categoryName;
    private String categoryDesc;

    public AddCategoryRequest() {
    }

    public AddCategoryRequest(String categoryName, String categoryDesc) {
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
