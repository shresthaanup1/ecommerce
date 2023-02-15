package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.RolesDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.model.UpdateRolesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Roles> listRoles() {
        List<RolesDTO> rolesDTOs = rolesDAO.findAll();
        List<Roles> roles =new ArrayList<>();
         for(RolesDTO rolesDTO: rolesDTOs){
             roles.add(new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription()));
         }
        return roles;
    }

    @Override
    public Roles getRolesById(Long id) {
        Optional<RolesDTO> optionalrolesDTO = rolesDAO.findById(id);
        Roles roles = null;
        if (optionalrolesDTO.isPresent()) {
            RolesDTO rolesDTO = optionalrolesDTO.get();
            roles = new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());
        }
        return roles;
    }

    @Override
    public void deleteRolesById(Long id) {
        rolesDAO.deleteById(id);
    }

   /* @Override
     public Roles updateRoles(UpdateRolesRequest updateRolesRequest) {

     return null;
 }*/

}
