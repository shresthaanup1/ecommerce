package com.ecommerce.productservices.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class APIConfig {
    @Value("${auth.base.url}")
    private String authBaseURL;
    @Value("${get.users.end.point}")
    private String getUsersEndpoint;

}