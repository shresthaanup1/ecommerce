package com.ecommerce.userservices.service;

import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.model.UpdateRolesRequest;

import java.util.List;

public interface RolesService{
    Roles addRoles(AddRolesRequest addRolesRequest);
    List<Roles> listRoles();
    Roles getRolesById(Long id);
    void deleteRolesById(Long id);

   // Roles updateRoles(UpdateRolesRequest updateRolesRequest);
    //Roles updateRolesByPatch(UpdateRolesRequest updateRolesRequest);

}
