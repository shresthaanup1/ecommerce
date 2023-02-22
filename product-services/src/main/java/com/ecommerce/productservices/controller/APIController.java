package com.ecommerce.productservices.controller;

import com.ecommerce.productservices.model.UserDetails;
import com.ecommerce.productservices.service.APIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class APIController {
    @Autowired
    private APIService apiService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("v1/api/users")
    public ResponseEntity<UserDetails> getUserDetails(@RequestParam Long userId) throws JsonProcessingException {
         UserDetails userDetails = apiService.getUserDetails(userId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("v1/api/users/list")
    public List getUserDetailList() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        return restTemplate.exchange("http://localhost:9899/user/details/", HttpMethod.GET,entity, List.class).getBody();
    }
    /*  Error in Local Date for dateofBirth */
    @PostMapping("v1/api/users/list")
    public UserDetails addUserDetail(@RequestBody UserDetails list) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDetails> entity = new HttpEntity<UserDetails>(list,httpHeaders);
        return restTemplate.exchange("http://localhost:9899/user/details/", HttpMethod.POST,entity, UserDetails.class).getBody();
    }
}
