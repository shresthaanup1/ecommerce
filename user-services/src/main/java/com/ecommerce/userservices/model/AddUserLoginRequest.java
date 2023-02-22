package com.ecommerce.userservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AddUserLoginRequest {

    private String userId;
    @NotBlank(message="Username should not be blank.")
    private  String userName;
    @NotBlank(message="Username should not be blank.")
    private String password;
    private String email;
    private LocalDateTime createdAt;
    @JsonProperty
    private boolean isActive;
    private String roleId;

    public AddUserLoginRequest() {
    }

    public AddUserLoginRequest(String userId, String userName, String password, String email, LocalDateTime createdAt, boolean isActive, String roleId) {

        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
