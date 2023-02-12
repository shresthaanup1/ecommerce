package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.RolesDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    private RolesDAO rolesDAO;
    @Override
    public Roles addRoles(AddRolesRequest addRolesRequest){
        RolesDTO rolesDTO = new RolesDTO(addRolesRequest.getRoleName(), addRolesRequest.getRoleDescription());
        rolesDTO = rolesDAO.save(rolesDTO);
        return new Roles(rolesDTO.getId(), rolesDTO.getRoleName(),rolesDTO.getRoleDescription());
    }
}
