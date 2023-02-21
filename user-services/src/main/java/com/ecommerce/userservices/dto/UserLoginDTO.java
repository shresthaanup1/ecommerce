package com.ecommerce.userservices.dto;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(schema="ecommerce", name ="user_login")
public class UserLoginDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_details_id")
    private String userId;
    @Column(name="user_name",unique = true, nullable = false, length = 100)
    private String userName;
    @Column(name="user_password" , nullable = false, length = 100)
    private String password;
    @Column(name="user_email")
    private String email;
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @Column(name ="last_login")
    private LocalDateTime lastLogin;
    @Column(name ="is_active")
    private boolean isActive;
    @Column(name ="user_role_id")
    private String roleId;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String userId, String userName, String password, String email,
                     LocalDateTime createdAt, LocalDateTime lastLogin, boolean isActive, String roleId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}


