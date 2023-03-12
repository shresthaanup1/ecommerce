package com.ecommerce.productservices.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLogin {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private boolean isActive;
    private String roleName;
    private Long userId;

}
