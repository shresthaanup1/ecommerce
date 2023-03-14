package com.ecommerce.userservices.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                            .title("Ecommerce API")
                            .description("User Service Module for Ecommerce application")
                            .version("v0.0.1")
                    )
                ;
    }
}
