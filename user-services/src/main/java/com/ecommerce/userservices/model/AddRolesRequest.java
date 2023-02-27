package com.ecommerce.userservices.model;

import javax.validation.constraints.NotBlank;

public class AddRolesRequest {
    @NotBlank(message = "Role name should be blank")
    private String roleName;
    @NotBlank(message = "Role description should be blank")
    private String roleDescription;

    public AddRolesRequest() {
    }

    public AddRolesRequest(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}