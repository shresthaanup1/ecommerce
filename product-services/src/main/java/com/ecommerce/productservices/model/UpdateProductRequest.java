package com.ecommerce.productservices.model;

import com.ecommerce.productservices.validation.IsPresentAndValid;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UpdateProductRequest {
    private Long id;
    @IsPresentAndValid(message="Product name should not be blank.")
    private String productName;
    @NotNull(message="Product price should not be blank.")
    private Double productPrice;
    @NotNull(message="Product isActive should not be blank.")
    private Boolean isActive;
    private Boolean isAvailable;
    private LocalDateTime createdAt;
    @NotNull(message="Category name should not be blank.")
    private Long categoryId;

    public UpdateProductRequest() {
    }

    public UpdateProductRequest(Long id, String productName, Double productPrice, Boolean isActive, Boolean isAvailable, LocalDateTime createdAt, Long categoryId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isActive = isActive;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
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
}
