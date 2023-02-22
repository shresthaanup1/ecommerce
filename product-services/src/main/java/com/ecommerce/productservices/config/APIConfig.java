package com.ecommerce.productservices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {
    @Value("${base.url}")
    private String baseURL;
    @Value("${get.users.end.point}")
    private String getUsersEndpoint;
    @Value("${create.users.end.point}")
    private String createUsersEndpoint;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getGetUsersEndpoint() {
        return getUsersEndpoint;
    }

    public void setGetUsersEndpoint(String getUsersEndpoint) {
        this.getUsersEndpoint = getUsersEndpoint;
    }

    public String getCreateUsersEndpoint() {
        return createUsersEndpoint;
    }

    public void setCreateUsersEndpoint(String createUsersEndpoint) {
        this.createUsersEndpoint = createUsersEndpoint;
    }
}
