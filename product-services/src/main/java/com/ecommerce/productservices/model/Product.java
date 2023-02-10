package com.ecommerce.productservices.model;

import com.ecommerce.productservices.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class Product {
    private Long id;

    private String productName;

    private Double productPrice;

    private boolean isActive;

    private boolean isAvailable;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;


    private CategoryDTO categoryDTO;

    public Product() {
    }

    public Product(Long id, String productName, Double productPrice, boolean isActive, boolean isAvailable, LocalDateTime createdAt, CategoryDTO categoryDTO) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isActive = isActive;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.categoryDTO = categoryDTO;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }
}
