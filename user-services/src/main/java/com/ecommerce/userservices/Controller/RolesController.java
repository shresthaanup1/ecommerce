package com.ecommerce.userservices.Controller;

import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.service.RolesService;
import com.ecommerce.userservices.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class RolesController {
    @Autowired
    private RolesService rolesService;
    @PostMapping(value ="/role")
    public ResponseEntity<Roles> addRoles(@RequestBody AddRolesRequest addRolesRequest){
        return new ResponseEntity<>(rolesService.addRoles(addRolesRequest), HttpStatus.OK);
    }
    @GetMapping(value = "/role")
    public ResponseEntity<List<Roles>> listRoles() {
        return new ResponseEntity<>(rolesService.listRoles(), HttpStatus.OK);
    }
    @GetMapping(value ="/role/{id}")
    public ResponseEntity<Roles> getRolesById(@PathVariable Long id){
        return new ResponseEntity<>(rolesService.getRolesById(id), HttpStatus.FOUND);
    }
    @DeleteMapping(value ="/role/{id}")
   public ResponseEntity<HttpStatus>deleteRolesById(@PathVariable Long id){
        rolesService.deleteRolesById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
