package com.ecommerce.userservices.service;

import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;

public interface RolesService{
    Roles addRoles(AddRolesRequest addRolesRequest);
}
