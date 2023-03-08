package com.ecommerce.userservices.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(schema="ecommerce", name ="user_login")
public class UserLoginDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name="user_name",unique = true, nullable = false, length = 100)
    private String userName;
    @NonNull
    @Column(name="user_password" , nullable = false, length = 100)
    private String password;
    @NonNull
    @Column(name="user_email")
    private String email;
    @NonNull
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @NonNull
    @Column(name ="last_login")
    private LocalDateTime lastLogin;
    @NonNull
    @Column(name ="is_active")
    private boolean isActive;
    @NonNull
    @ManyToOne(fetch =FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RolesDTO rolesDTO;
    @NonNull
    @OneToOne(fetch = FetchType.LAZY, optional =false)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id",unique = true)
     private UserDetailsDTO userDetailsDTO;
}


