package com.ecommerce.productservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AddProductRequest {
    private String productName;
    private Double productPrice;
    @JsonProperty
    private boolean isActive;
    @JsonProperty
    private boolean isAvailable;
    private LocalDateTime createdAt;
    private Long categoryId;

    public AddProductRequest() {
    }
    public AddProductRequest(String productName, Double productPrice, boolean isActive
            , boolean isAvailable
            , LocalDateTime createdAt
            , Long categoryId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.isActive = isActive;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.categoryId = categoryId;

    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    /*
    * https://stackoverflow.com/questions/21913955/json-post-request-for-boolean-field-sends-false-by-default
    * Remember that Jackson, by default, determines the property name from either the getter or setter (the first that matches).
    * If you are not using @JsonProperty for is_Available and is_Active then you need to change your getter and setter as follows:
    * public boolean getIsActive() {
           return isActive;
       }

       public void setIsActive(boolean isActive) {
            this.isActive = isActive;
         }
    * */
}
