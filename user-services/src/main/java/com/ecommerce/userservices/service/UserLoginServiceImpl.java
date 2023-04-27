package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.RolesDAO;
import com.ecommerce.userservices.dao.UserDetailsDAO;
import com.ecommerce.userservices.dao.UserLoginDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.dto.UserDetailsDTO;
import com.ecommerce.userservices.dto.UserLoginDTO;
import com.ecommerce.userservices.exception.JsonParameterNotValidException;
import com.ecommerce.userservices.exception.RolesNotFoundException;
import com.ecommerce.userservices.exception.UserDetailsNotFoundException;
import com.ecommerce.userservices.model.AddUserLoginRequest;
import com.ecommerce.userservices.model.SignUpRequest;
import com.ecommerce.userservices.model.UpdateUserLoginRequest;
import com.ecommerce.userservices.model.UserLogin;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserloginService {

    private final UserLoginDAO userLoginDAO;
    private final RolesDAO rolesDAO;
    private  final UserDetailsDAO userDetailsDAO;
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserLogin addUserLogin(AddUserLoginRequest addUserLoginRequest) {

            Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(addUserLoginRequest.getRoleId());
            RolesDTO rolesDTO = RolesServiceImpl.unwrapRolesDTO(optionalRolesDTO, addUserLoginRequest.getRoleId());

            Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(addUserLoginRequest.getUserId());
            //if user details does not exist then throw error
            if (optionalUserDetailsDTO.isPresent()) {
                UserDetailsDTO userDetailsDTO = UserLoginServiceImpl.unwrapUserDetailsDTO(optionalUserDetailsDTO, addUserLoginRequest.getUserId());
                String password = bCryptPasswordEncoder.encode(addUserLoginRequest.getPassword());
                UserLoginDTO userLoginDTO = new UserLoginDTO(addUserLoginRequest.getUserName(),
                        password,
                        addUserLoginRequest.getEmail(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        addUserLoginRequest.isActive(),
                        rolesDTO,
                        userDetailsDTO);
                userLoginDTO = userLoginDAO.save(userLoginDTO);
                return new UserLogin(userLoginDTO.getId(),
                        userLoginDTO.getUserName(),
                        userLoginDTO.getPassword(),
                        userLoginDTO.getEmail(),
                        userLoginDTO.getCreatedAt(),
                        userLoginDTO.getLastLogin(),
                        userLoginDTO.isActive(),
                        userLoginDTO.getRolesDTO().getRoleName(),
                        userLoginDTO.getUserDetailsDTO().getId()); // we are returning from user details
            }else{
                throw new UserDetailsNotFoundException(addUserLoginRequest.getUserId());
            }
    }

    @Override
    public List<UserLogin> listUserLogin() {
        List<UserLoginDTO> userLoginDTOs = userLoginDAO.findAll();
        List<UserLogin> userLogins = new ArrayList<>();

        for (UserLoginDTO userLoginDTO : userLoginDTOs) {
            UserLogin userLogin = new UserLogin(userLoginDTO.getId(),
                    userLoginDTO.getUserName(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(),
                    userLoginDTO.getLastLogin(),
                    userLoginDTO.isActive(),
                    userLoginDTO.getRolesDTO().getRoleName(),
                    userLoginDTO.getUserDetailsDTO().getId());
            userLogins.add(userLogin);
        }
        return userLogins;
    }

    @Override
    public UserLogin getUserLoginById(Long id) {
        Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(id);

        if (optionalUserLoginDTO.isPresent()) {
            UserLoginDTO userLoginDTO = optionalUserLoginDTO.get();

            UserLogin userLogin = new UserLogin(userLoginDTO.getId(),
                    userLoginDTO.getUserName(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(),
                    userLoginDTO.getLastLogin(),
                    userLoginDTO.isActive(),
                    userLoginDTO.getRolesDTO().getRoleName(),
                    userLoginDTO.getUserDetailsDTO().getId());
            return userLogin;
        }
        return null;
    }

    @Override
    public void deleteUserLoginById(Long id) {
        Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(id);
        if (optionalUserLoginDTO.isPresent()) {
            userLoginDAO.deleteById(id);
        }
    }

    @Override
    public UserLogin updateUserLogin(UpdateUserLoginRequest updateUserLoginRequest) {
        if (updateUserLoginRequest.getId() != null) {
            Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(updateUserLoginRequest.getId());
            UserLoginDTO userLoginDTO = unwrapUserLoginDTO(optionalUserLoginDTO, updateUserLoginRequest.getId());

            Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(updateUserLoginRequest.getRoleId());
            RolesDTO rolesDTO = RolesServiceImpl.unwrapRolesDTO(optionalRolesDTO, updateUserLoginRequest.getRoleId());

            Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(updateUserLoginRequest.getUserId());
            UserDetailsDTO userDetailsDTO = UserLoginServiceImpl.unwrapUserDetailsDTO(optionalUserDetailsDTO, updateUserLoginRequest.getUserId());
                    userLoginDTO.setUserName(updateUserLoginRequest.getUserName());
                    userLoginDTO.setPassword(bCryptPasswordEncoder.encode(updateUserLoginRequest.getPassword()));
                    userLoginDTO.setEmail(updateUserLoginRequest.getEmail());
                    userLoginDTO.setCreatedAt(LocalDateTime.now());
                    userLoginDTO.setLastLogin(LocalDateTime.now());
                    userLoginDTO.setActive(updateUserLoginRequest.getIsActive());
                    userLoginDTO.setRolesDTO(rolesDTO);
                    userLoginDTO.setUserDetailsDTO(userDetailsDTO);
                    userLoginDTO = userLoginDAO.save(userLoginDTO);
            return new UserLogin(userLoginDTO.getId(),
                    userLoginDTO.getUserName(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(),
                    userLoginDTO.getLastLogin(),
                    userLoginDTO.isActive(),
                    userLoginDTO.getRolesDTO().getRoleName(),
                    userLoginDTO.getUserDetailsDTO().getId());
        } else {
            throw new JsonParameterNotValidException("id");
        }
    }

    @Override
    public UserLogin patchUserLogin(UpdateUserLoginRequest updateuserLoginRequest) {
        if (updateuserLoginRequest.getId() != null) {
            Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findById(updateuserLoginRequest.getId());
            UserLoginDTO userLoginDTO = unwrapUserLoginDTO(optionalUserLoginDTO, updateuserLoginRequest.getId());
            if (updateuserLoginRequest.getUserName() != null) {
                userLoginDTO.setUserName(updateuserLoginRequest.getUserName());
            }
            if (updateuserLoginRequest.getPassword() != null) {
                userLoginDTO.setPassword(bCryptPasswordEncoder.encode(updateuserLoginRequest.getPassword()));
            }
            if (updateuserLoginRequest.getEmail() != null) {
                userLoginDTO.setEmail(updateuserLoginRequest.getEmail());
            }
            if (updateuserLoginRequest.getCreatedAt() != null) {
                userLoginDTO.setCreatedAt(LocalDateTime.now());
            }
            if (updateuserLoginRequest.getLastLogin() != null) {
                userLoginDTO.setLastLogin(LocalDateTime.now());
            }
            if (updateuserLoginRequest.getIsActive() != userLoginDTO.isActive()) {
                userLoginDTO.setActive(updateuserLoginRequest.getIsActive());
            }
            if (updateuserLoginRequest.getRoleId() != null) {
                Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(updateuserLoginRequest.getRoleId());
                RolesDTO rolesDTO = RolesServiceImpl.unwrapRolesDTO(optionalRolesDTO, updateuserLoginRequest.getRoleId());
                userLoginDTO.setRolesDTO(rolesDTO);
            }
            if (updateuserLoginRequest.getUserId() != null) {
                Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(updateuserLoginRequest.getUserId());
                UserDetailsDTO userDetailsDTO = UserLoginServiceImpl.unwrapUserDetailsDTO(optionalUserDetailsDTO, updateuserLoginRequest.getUserId());
                userLoginDTO.setUserDetailsDTO(userDetailsDTO);
            }
            userLoginDTO = userLoginDAO.save(userLoginDTO);
            return new UserLogin(userLoginDTO.getId(),
                    userLoginDTO.getUserName(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(),
                    userLoginDTO.getLastLogin(),
                    userLoginDTO.isActive(),
                    userLoginDTO.getRolesDTO().getRoleName(),
                    userLoginDTO.getUserDetailsDTO().getId());
        } else {
            throw new JsonParameterNotValidException("id");
        }
    }

    @Override
    public UserLogin getUserLoginByUserName(String userName) {
        Optional<UserLoginDTO> optionalUserLoginDTO = userLoginDAO.findByUserName(userName);

        if (optionalUserLoginDTO.isPresent()) {
            UserLoginDTO userLoginDTO = optionalUserLoginDTO.get();

            UserLogin userLogin = new UserLogin(userLoginDTO.getId(),
                    userLoginDTO.getUserName(),
                    userLoginDTO.getPassword(),
                    userLoginDTO.getEmail(),
                    userLoginDTO.getCreatedAt(),
                    userLoginDTO.getLastLogin(),
                    userLoginDTO.isActive(),
                    userLoginDTO.getRolesDTO().getRoleName(),
                    userLoginDTO.getUserDetailsDTO().getId());
            return userLogin;
        }
        return null;
    }

    @Override
    public SignUpRequest registerUser(SignUpRequest signUpRequest) {
        /* Role user with id 2 in role table is a must */
        Optional<RolesDTO> optionalRolesDTO = rolesDAO.findById(2L);
        RolesDTO rolesDTO = RolesServiceImpl.unwrapRolesDTO(optionalRolesDTO, 2L);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(UUID.randomUUID().toString(),
                signUpRequest.getFirstname(),
                signUpRequest.getMiddlename(),
                signUpRequest.getLastname(),
                signUpRequest.getDob(),
                signUpRequest.getAddress());
        userDetailsDTO = userDetailsDAO.save(userDetailsDTO);
        /* if some error with user details do not insert in login table as well */

        Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(userDetailsDTO.getId());
        if (optionalUserDetailsDTO.isPresent()) {
            String password = bCryptPasswordEncoder.encode(signUpRequest.getPassword());
            UserLoginDTO userLoginDTO = new UserLoginDTO(signUpRequest.getUsername(),
                    password,
                    signUpRequest.getEmail(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    true,
                    rolesDTO,
                    userDetailsDTO);
            userLoginDTO = userLoginDAO.save(userLoginDTO);
        } else {
            throw new UserDetailsNotFoundException(userDetailsDTO.getId());
        }
        return null;
    }

    private UserLoginDTO unwrapUserLoginDTO(Optional<UserLoginDTO> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        return null;
        // else throw new RolesNotFoundException(id);
    }

    private static UserDetailsDTO unwrapUserDetailsDTO(Optional<UserDetailsDTO> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        return null;
    }
}