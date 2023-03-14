package com.ecommerce.userservices.controller;

import com.ecommerce.userservices.exception.ErrorResponse;
import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.model.UpdateRolesRequest;
import com.ecommerce.userservices.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Tag(name="Roles",description = "For managing different user roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @Operation(summary="Adds new role",description = "Adds new role and returns the added role with newly generated id")
    @ApiResponse(responseCode = "200", description = "Successful addition of role", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Roles.class))))
    @PostMapping(value ="/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Roles> addRoles(@RequestBody AddRolesRequest addRolesRequest){
        return new ResponseEntity<>(rolesService.addRoles(addRolesRequest), HttpStatus.OK);
    }

    @Operation(summary="Retrieves all existing roles",description = "Retrieves all existing roles and gives list")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of roles", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Roles.class))))
    @GetMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Roles>> listRoles() {
        return new ResponseEntity<>(rolesService.listRoles(), HttpStatus.OK);
    }

    @Operation(summary="Retrieves existing role",description = "Retrieves the existing role based on id provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of role", content = @Content(schema = @Schema(implementation = Roles.class))),
    })
    @GetMapping(value ="/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Roles> getRolesById(@PathVariable Long id){
        return new ResponseEntity<>(rolesService.getRolesById(id), HttpStatus.OK);
    }

    @Operation(summary="Deletes existing role",description = "Deletes the existing role based on id provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "204", description = "Successful deletion of role", content = @Content(schema = @Schema(implementation = Roles.class))),
    })
    @DeleteMapping(value ="/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<HttpStatus>deleteRolesById(@PathVariable Long id){
        rolesService.deleteRolesById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary="Updates existing role",description = "Updates the existing role based on id provided. This replaces whole object in the database with provided values in the request body, if the field is not provided null value is inserted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "202", description = "Successful update of role", content = @Content(schema = @Schema(implementation = Roles.class))),
    })
    @PutMapping(value ="/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Roles>updateRoles(@RequestBody UpdateRolesRequest updateRolesRequest){
        return new ResponseEntity<>(rolesService.updateRoles(updateRolesRequest), HttpStatus.ACCEPTED);
    }

    @Operation(summary="Updates existing role",description = "Updates the existing role based on id and fields provided for update. This will only update those fields which are provided in the request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "202", description = "Successful update of role", content = @Content(schema = @Schema(implementation = Roles.class))),
    })
    @Tags( {
            //@Tag(name = "Roles"),
            @Tag(name="Update",description = "For updating roles. Note:Added just for testing purpose of @Tags annotation.")
    })
    @PatchMapping(value="/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Roles> patchRoles(@RequestBody UpdateRolesRequest updateRolesRequest){
        return new ResponseEntity<>(rolesService.patchRoles(updateRolesRequest),HttpStatus.ACCEPTED);
    }
}
