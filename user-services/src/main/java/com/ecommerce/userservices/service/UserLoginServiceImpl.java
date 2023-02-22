package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.UserLoginDAO;
import com.ecommerce.userservices.dto.UserLoginDTO;
import com.ecommerce.userservices.model.AddUserLoginRequest;
import com.ecommerce.userservices.model.UpdateUserLoginRequest;
import com.ecommerce.userservices.model.UserLogin;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserloginService {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Override
    public UserLogin addUserLogin(AddUserLoginRequest addUserLoginRequest) {
        UserLoginDTO userLoginDTO =  new UserLoginDTO();
        userLoginDTO.setUserId(addUserLoginRequest.getUserId());
        userLoginDTO.setUserName(addUserLoginRequest.getUserName());
        userLoginDTO.setPassword(addUserLoginRequest.getPassword());
        userLoginDTO.setEmail(addUserLoginRequest.getEmail());
        userLoginDTO.setCreatedAt(LocalDateTime.now());
        userLoginDTO.setActive(addUserLoginRequest.isActive());
        userLoginDTO.setRoleId(addUserLoginRequest.getRoleId());

        userLoginDTO = userLoginDAO.save(userLoginDTO);
        return new UserLogin(userLoginDTO.getId(), userLoginDTO.getUserId(), userLoginDTO.getUserName(),
                userLoginDTO.getPassword(), userLoginDTO.getEmail(), userLoginDTO.getCreatedAt(),
                userLoginDTO.getLastLogin(), userLoginDTO.isActive(), userLoginDTO.getRoleId());
    }

    @Override
    public List<UserLogin> listUserLogin() {
        List<UserLoginDTO> userLoginDTOs = userLoginDAO.findAll();
        List<UserLogin> userLogins = new ArrayList<>();
        for (UserLoginDTO userLoginDTO : userLoginDTOs) {

            UserLogin userLogin = new UserLogin(userLoginDTO.getId(), userLoginDTO.getUserId(),
                    userLoginDTO.getUserName(), userLoginDTO.getPassword(), userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(), userLoginDTO.getLastLogin(), userLoginDTO.isActive(),
                    userLoginDTO.getRoleId());
            userLogins.add(userLogin);
            }
            return userLogins;
    }

    @Override
    public UserLogin getUserLoginById(Long id) {
        Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(id);
        if (optionalUserLoginDTO.isPresent()) {
            UserLoginDTO userLoginDTO = optionalUserLoginDTO.get();

            UserLogin userLogin = new UserLogin(userLoginDTO.getId(), userLoginDTO.getUserId(),
                    userLoginDTO.getUserName(), userLoginDTO.getPassword(), userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(), userLoginDTO.getLastLogin(), userLoginDTO.isActive(),
                    userLoginDTO.getRoleId());
            return userLogin;
        }
        return null;
    }

    @Override
    public void deleteUserLoginById(Long id) {
        Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(id);
        if(optionalUserLoginDTO.isPresent()) {
            userLoginDAO.deleteById(id);
        }
    }

    @Override
    public UserLogin updateUserLogin(UpdateUserLoginRequest updateUserLoginRequest) {
        if(updateUserLoginRequest.getId() !=null) {
            Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(updateUserLoginRequest.getId());
            if(optionalUserLoginDTO.isPresent()){
                UserLoginDTO userLoginDTO =optionalUserLoginDTO.get();
                userLoginDTO.setUserId(updateUserLoginRequest.getUserId());
                userLoginDTO.setUserName(updateUserLoginRequest.getUserName());
                userLoginDTO.setPassword(updateUserLoginRequest.getPassword());
                userLoginDTO.setEmail(updateUserLoginRequest.getEmail());
                userLoginDTO.setCreatedAt(LocalDateTime.now());
                userLoginDTO.setLastLogin(LocalDateTime.now());
                userLoginDTO.setActive(updateUserLoginRequest.getIsActive());
                userLoginDTO.setRoleId(updateUserLoginRequest.getRoleId());

                userLoginDTO = userLoginDAO.save(userLoginDTO);
                return new UserLogin(userLoginDTO.getId(), userLoginDTO.getUserId(),
                        userLoginDTO.getUserName(), userLoginDTO.getPassword(), userLoginDTO.getEmail(),
                        userLoginDTO.getCreatedAt(), userLoginDTO.getLastLogin(), userLoginDTO.isActive(),
                        userLoginDTO.getRoleId());
            }
        }return null;
    }

    @Override
    public UserLogin patchUserLogin(UpdateUserLoginRequest updateuserLoginRequest) {
        if(updateuserLoginRequest.getId() !=null){
            Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(updateuserLoginRequest.getId());
            if(optionalUserLoginDTO.isPresent()){
                UserLoginDTO userLoginDTO = optionalUserLoginDTO.get();
                if(updateuserLoginRequest.getUserId() !=null){
                    userLoginDTO.setUserId(updateuserLoginRequest.getUserId());
                }
                if(updateuserLoginRequest.getUserName() !=null){
                    userLoginDTO.setUserName(updateuserLoginRequest.getUserName());
                }
                if(updateuserLoginRequest.getPassword()!=null){
                    userLoginDTO.setPassword(updateuserLoginRequest.getPassword());
                }
                if(updateuserLoginRequest.getEmail()!=null){
                    userLoginDTO.setEmail(updateuserLoginRequest.getEmail());
                }
                if(updateuserLoginRequest.getCreatedAt() !=null){
                    userLoginDTO.setCreatedAt(LocalDateTime.now());
                }
                if(updateuserLoginRequest.getLastLogin()!=null){
                    userLoginDTO.setLastLogin(LocalDateTime.now());
                }
                if(updateuserLoginRequest.getIsActive() !=userLoginDTO.isActive()){
                    userLoginDTO.setActive(updateuserLoginRequest.getIsActive());
                }
                if(updateuserLoginRequest.getRoleId() !=null){
                    userLoginDTO.setRoleId(updateuserLoginRequest.getRoleId());
                }
                userLoginDTO = userLoginDAO.save(userLoginDTO);
                return new UserLogin(userLoginDTO.getId(), userLoginDTO.getUserId(),
                        userLoginDTO.getUserName(), userLoginDTO.getPassword(), userLoginDTO.getEmail(),
                        userLoginDTO.getCreatedAt(), userLoginDTO.getLastLogin(), userLoginDTO.isActive(),
                        userLoginDTO.getRoleId());
            }
        }
        return null;
    }
}
