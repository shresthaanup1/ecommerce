package com.ecommerce.userservices.service;

import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UpdateUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;

import java.util.List;

public interface UserDetailsService {

    UserDetails addUserDetails(AddUserDetailsRequest addUserDetailsRequest);

    List<UserDetails> listUserDetails();

    UserDetails getUserDetailsById(Long id);

    void deleteUserDetailsById(Long id);

    UserDetails updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest);

    UserDetails patchUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest);
}
