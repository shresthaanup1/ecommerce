package com.ecommerce.userservices.service;


import com.ecommerce.userservices.model.AddUserLoginRequest;
import com.ecommerce.userservices.model.UpdateRolesRequest;
import com.ecommerce.userservices.model.UpdateUserLoginRequest;
import com.ecommerce.userservices.model.UserLogin;

import java.util.List;

public interface UserloginService {
    UserLogin addUserLogin(AddUserLoginRequest addRolesRequest);

    List<UserLogin> listUserLogin();

    UserLogin getUserLoginById(Long id);

    void deleteUserLoginById(Long id);

    UserLogin updateUserLogin(UpdateUserLoginRequest updateUserLoginRequest);

    UserLogin patchUserLogin(UpdateUserLoginRequest updateuserLoginRequest);
}