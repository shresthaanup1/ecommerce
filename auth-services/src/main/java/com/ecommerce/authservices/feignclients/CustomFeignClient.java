package com.ecommerce.authservices.feignclients;

import com.ecommerce.authservices.model.Roles;
import com.ecommerce.authservices.model.UserLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="api-gateway")
public interface CustomFeignClient {
    @GetMapping(value ="/user/role/{id}")
    public Roles getRolesById(@PathVariable Long id);

    @GetMapping(value ="/user/login/{userName}")
    public UserLogin getUserLoginByUsername(@PathVariable String userName);
}
