package com.ecommerce.userservices.model;

import com.ecommerce.userservices.dto.RolesDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLogin {
    private Long id;
    private String userName;
    private String password;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLogin;
    private boolean isActive;
    private String roleName;
    private String userId;
/*
    public UserLogin() {
    }

    public UserLogin(Long id, String userName, String password, String email, LocalDateTime createdAt,
                     LocalDateTime lastLogin, boolean isActive, String roleName, String userId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.roleName = roleName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserDetailsName(String userDetailsName) {
        userId = userId;
    }
*/
}
