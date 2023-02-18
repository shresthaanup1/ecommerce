package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.UserDetailsDAO;
import com.ecommerce.userservices.dto.UserDetailsDTO;
import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsDAO userDetailsDAO;
    @Override
    public UserDetails addUserDetails(AddUserDetailsRequest addUserDetailsRequest) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(addUserDetailsRequest.getFirstName(),
                addUserDetailsRequest.getMiddleName(),addUserDetailsRequest.getLastName(),
                addUserDetailsRequest.getDateOfBirth(), addUserDetailsRequest.getAddress());
        userDetailsDTO = userDetailsDAO.save(userDetailsDTO);
        return new UserDetails(userDetailsDTO.getId(),userDetailsDTO.getFirstName(),
                userDetailsDTO.getMiddleName(),userDetailsDTO.getLastName(),
                userDetailsDTO.getDateofBirth(), userDetailsDTO.getAddress());
    }
}
