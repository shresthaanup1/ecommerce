package com.ecommerce.userservices.model;

public class AddRolesRequest {
    private String roleName;
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