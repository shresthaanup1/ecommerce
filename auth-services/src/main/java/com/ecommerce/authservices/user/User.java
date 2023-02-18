package com.ecommerce.authservices.user;

public class User {
    private Long id;
    private String userName;
    private String userEmail;

    public User() {
    }

    public User(Long id, String userName, String userEmail) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
