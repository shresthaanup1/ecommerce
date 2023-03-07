package com.ecommerce.userservices.dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(schema="ecommerce", name="user_roles")
public class RolesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "role_name")
    private String roleName;
    @NonNull
    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(mappedBy="rolesDTO", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<UserLoginDTO> userLoginDTOList;

    /*
    public RolesDTO() {
    }
    public RolesDTO(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }*/

    }