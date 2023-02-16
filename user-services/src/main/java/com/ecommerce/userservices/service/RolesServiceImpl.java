package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.RolesDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.exception.RolesNotFoundException;
import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.model.UpdateRolesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesDAO rolesDAO;

    @Override
    public Roles addRoles(AddRolesRequest addRolesRequest) {
        RolesDTO rolesDTO = new RolesDTO(addRolesRequest.getRoleName(), addRolesRequest.getRoleDescription());
        rolesDTO = rolesDAO.save(rolesDTO);
        return new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());
    }

    @Override
    public List<Roles> listRoles() {
        List<RolesDTO> rolesDTOs = rolesDAO.findAll();
        List<Roles> roles = new ArrayList<>();
        for (RolesDTO rolesDTO : rolesDTOs) {
            roles.add(new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription()));
        }
        return roles;
    }

    @Override
    public Roles getRolesById(Long id) {
        Optional<RolesDTO> optionalrolesDTO = rolesDAO.findById(id);
        if (optionalrolesDTO.isPresent()) {
            RolesDTO rolesDTO = optionalrolesDTO.get();
           Roles roles = new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());
           return roles;
        }else{
            throw new RolesNotFoundException(id);
        }
    }

    @Override
    public void deleteRolesById(Long id) {
        Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(id);
        if(optionalRolesDTO.isPresent()){
            rolesDAO.deleteById(id);
        }else {
            throw new RolesNotFoundException(id);
        }
    }

    @Override
    public Roles updateRoles(UpdateRolesRequest updateRolesRequest) {
        //get Id from request body
        // updateRolesRequest.getId();
        //Check if the ID is not null or not
        // if its Null throw exception
        // if not do regular stuff
        // check if the ID is there in the DB or not
        //rolesDAO.findById(updateRolesRequest.getId());
//        RolesDTO rolesDTO = rolesDAO.findById(updateRolesRequest.getId()).get();
//        rolesDTO.setRoleName(updateRolesRequest.getRoleName());
//        rolesDTO.setRoleDescription(updateRolesRequest.getRoleDescription());
//        rolesDAO.save(rolesDTO);
//        return new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());

        // copy value from request body to objecgt from the database
        Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(updateRolesRequest.getId());
        if (optionalRolesDTO.isPresent()) {
            RolesDTO rolesDTO = optionalRolesDTO.get();
            rolesDTO.setRoleName(updateRolesRequest.getRoleName());
            rolesDTO.setRoleDescription(updateRolesRequest.getRoleDescription());
            rolesDTO = rolesDAO.save(rolesDTO);
            Roles updatedroles = new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());
            return updatedroles;
        }
        else {
            return new Roles();
        }
        // RolesDTO rolesDTO  =  rolesDAO.findById(updateRolesRequest.getId());
        // save the updated object to datbase
        // return newly created to controller
    }

    @Override
    public Roles patchRoles(UpdateRolesRequest updateRolesRequest) {
        Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(updateRolesRequest.getId());
        if (optionalRolesDTO.isPresent()) {
            RolesDTO rolesDTO = optionalRolesDTO.get();
            if(updateRolesRequest.getRoleName()!= null) {
                rolesDTO.setRoleName(updateRolesRequest.getRoleName());
            }
            if(updateRolesRequest.getRoleDescription()!=null) {
                rolesDTO.setRoleDescription(updateRolesRequest.getRoleDescription());
            }
            rolesDTO = rolesDAO.save(rolesDTO);
            Roles updatedroles = new Roles(rolesDTO.getId(), rolesDTO.getRoleName(), rolesDTO.getRoleDescription());
            return updatedroles;
        }
       return null;
    }
}