package com.ecommerce.userservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String firstname;
    private String middlename;
    private String lastname;
    private LocalDate dob;
    private String address;

    @NotBlank(message = "Username should not be blank.")
    private String username;
    @NotBlank(message = "Username should not be blank.")
    private String password;
    private String email;
    private LocalDateTime createdAt;

    private boolean isActive;
    private Long roleId;
    private Long userId;



}
