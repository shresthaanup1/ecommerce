package com.ecommerce.userservices.Controller;

import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class RolesController {
    @Autowired
    private RolesServiceImpl rolesServiceImpl;
    @PostMapping(value ="/role")
    public ResponseEntity<Roles> addRoles(@RequestBody AddRolesRequest addRolesRequest){
        return new ResponseEntity<>(rolesServiceImpl.addRoles(addRolesRequest), HttpStatus.OK);
    }
}
