package com.ecommerce.productservices.service;

import com.ecommerce.productservices.config.APIConfig;
import com.ecommerce.productservices.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private APIConfig apiConfig;
    public UserDetails getUserDetails(Long userId) throws JsonProcessingException {
        String apiURL = "http://localhost:9899/user/details/" + userId;
       // String apiURL = apiConfig.getBaseURL() + apiConfig.getGetUsersEndpoint() + userId;
        //use getForObject for GET Mapping
        String responseStr = restTemplate.getForObject(apiURL, String.class);
        //Deserialize the response(JSON object) from user details api into our UserDetails model class
        UserDetails response = new ObjectMapper().readValue(responseStr,UserDetails.class);

        UserDetails userDetails = new UserDetails();
        userDetails.setId(response.getId());
        userDetails.setAddress(response.getAddress());
        userDetails.setDateOfBirth(response.getDateOfBirth());
        userDetails.setFirstName(response.getFirstName());
        userDetails.setLastName(response.getLastName());


       // System.out.println(userDetails.getAddress());
        return userDetails;
    }
}
