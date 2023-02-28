package com.ecommerce.userservices.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class UpdateUserLoginRequest {

    private Long id;
    @NotBlank(message = "Username should not be blank.")
    private String userName;
    @NotBlank(message = "Username should not be blank.")
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    private boolean isActive;
    private Long roleId;
    private String userId;

    public UpdateUserLoginRequest() {
    }

    public UpdateUserLoginRequest(Long id, String userName, String password, String email, LocalDateTime createdAt, LocalDateTime lastLogin, boolean isActive, Long roleId, String userId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.roleId = roleId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
