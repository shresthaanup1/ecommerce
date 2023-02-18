package com.ecommerce.userservices.service;

import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;

public interface UserDetailsService {
    UserDetails addUserDetails(AddUserDetailsRequest addUserDetailsRequest);
}
