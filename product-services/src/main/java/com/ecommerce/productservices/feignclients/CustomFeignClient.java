package com.ecommerce.productservices.feignclients;


import com.ecommerce.productservices.model.UserLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="api-gateway", url = "${api-gateway.url}")
public interface CustomFeignClient {
    @GetMapping("/auth/getUserFromToken")
    public UserLogin getUserFromToken(@RequestHeader(value ="Authorization", required=false) String authHeader);

    @GetMapping("/auth/validateToken")
    public ResponseEntity<?> getUser(@RequestHeader(value ="Authorization", required = false) String authHeader);

    @GetMapping("/auth/getAuthorityFromToken")
    public String getAuthorityFromToken(@RequestHeader(value ="Authorization", required=false) String authHeader);
}
