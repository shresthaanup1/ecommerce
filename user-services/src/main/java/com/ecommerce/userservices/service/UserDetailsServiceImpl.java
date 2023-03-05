package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.UserDetailsDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.dto.UserDetailsDTO;
import com.ecommerce.userservices.exception.RolesNotFoundException;
import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UpdateUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final UserDetailsDAO userDetailsDAO;

    @Override
    public UserDetails addUserDetails(AddUserDetailsRequest addUserDetailsRequest) {

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(UUID.randomUUID().toString(),
                                        addUserDetailsRequest.getFirstName(),
                                        addUserDetailsRequest.getMiddleName(),
                                        addUserDetailsRequest.getLastName(),
                                         addUserDetailsRequest.getDateOfBirth(),
                                        addUserDetailsRequest.getAddress());
        userDetailsDTO = userDetailsDAO.save(userDetailsDTO);
        return new UserDetails(userDetailsDTO.getId(),
                                userDetailsDTO.getUserId(),
                                userDetailsDTO.getFirstName(),
                                userDetailsDTO.getMiddleName(),
                                userDetailsDTO.getLastName(),
                                userDetailsDTO.getDateofBirth(),
                                userDetailsDTO.getAddress());
    }

    @Override
    public List<UserDetails> listUserDetails() {

        List<UserDetailsDTO> userDetailsDTOs = userDetailsDAO.findAll();
        List<UserDetails> userDetails = new ArrayList<>();

        for (UserDetailsDTO userDetailsDTO : userDetailsDTOs) {
            userDetails.add(new UserDetails(userDetailsDTO.getId(),
                                            userDetailsDTO.getUserId(),
                                            userDetailsDTO.getFirstName(),
                                            userDetailsDTO.getMiddleName(),
                                            userDetailsDTO.getLastName(),
                                            userDetailsDTO.getDateofBirth(),
                                            userDetailsDTO.getAddress()));
        }
        return userDetails;
    }

    @Override
    public UserDetails getUserDetailsById(Long id) {
        Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(id);

        if (optionalUserDetailsDTO.isPresent()) {
            UserDetailsDTO userDetailsDTO = optionalUserDetailsDTO.get();

            UserDetails userDetails = new UserDetails(userDetailsDTO.getId(),
                                            userDetailsDTO.getUserId(),
                                            userDetailsDTO.getFirstName(),
                                            userDetailsDTO.getMiddleName(),
                                            userDetailsDTO.getLastName(),
                                            userDetailsDTO.getDateofBirth(),
                                            userDetailsDTO.getAddress());
            return userDetails;
        }
        return null;
    }

    @Override
    public void deleteUserDetailsById(Long id) {
        Optional<UserDetailsDTO> optionalCategoryDTO = userDetailsDAO.findById(id);

        if(optionalCategoryDTO.isPresent()) {
            userDetailsDAO.deleteById(id);
        }
    }

    @Override
    public UserDetails updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) {

        if(updateUserDetailsRequest.getId() !=null) {
            Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(updateUserDetailsRequest.getId());

            if (optionalUserDetailsDTO.isPresent()) {
                            UserDetailsDTO userDetailsDTO = optionalUserDetailsDTO.get();
                            userDetailsDTO.setFirstName(updateUserDetailsRequest.getFirstName());
                            userDetailsDTO.setMiddleName(updateUserDetailsRequest.getMiddleName());
                            userDetailsDTO.setLastName(updateUserDetailsRequest.getLastName());
                            userDetailsDTO.setDateofBirth(updateUserDetailsRequest.getDateOfBirth());
                            userDetailsDTO.setAddress(updateUserDetailsRequest.getAddress());
                userDetailsDTO = userDetailsDAO.save(userDetailsDTO);
                UserDetails userDetails = new UserDetails(userDetailsDTO.getId(),
                                                        userDetailsDTO.getUserId(),
                                                        userDetailsDTO.getFirstName(),
                                                        userDetailsDTO.getMiddleName(),
                                                        userDetailsDTO.getLastName(),
                                                        userDetailsDTO.getDateofBirth(),
                                                        userDetailsDTO.getAddress());

                return userDetails;
            }
        }
        return null;
    }

    @Override
    public UserDetails patchUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) {

        if(updateUserDetailsRequest.getId() !=null){
            Optional<UserDetailsDTO> optionalUserDetailsDTO = userDetailsDAO.findById(updateUserDetailsRequest.getId());

            if(optionalUserDetailsDTO.isPresent()){
                UserDetailsDTO userDetailsDTO = optionalUserDetailsDTO.get();

                if(updateUserDetailsRequest.getFirstName() !=null){
                    userDetailsDTO.setFirstName(updateUserDetailsRequest.getFirstName());
                }
                if(updateUserDetailsRequest.getMiddleName() !=null){
                    userDetailsDTO.setMiddleName(updateUserDetailsRequest.getMiddleName());
                }
                if(updateUserDetailsRequest.getLastName() !=null){
                    userDetailsDTO.setLastName(updateUserDetailsRequest.getLastName());
                }
                if(updateUserDetailsRequest.getDateOfBirth() !=null ){
                    userDetailsDTO.setDateofBirth((updateUserDetailsRequest.getDateOfBirth()));
                }
                if(updateUserDetailsRequest.getAddress() !=null){
                    userDetailsDTO.setAddress(updateUserDetailsRequest.getAddress());
                }
                userDetailsDTO = userDetailsDAO.save(userDetailsDTO);

                return new UserDetails(userDetailsDTO.getId(),
                                        userDetailsDTO.getUserId(),
                                        userDetailsDTO.getFirstName(),
                                        userDetailsDTO.getMiddleName(),
                                        userDetailsDTO.getLastName(),
                                        userDetailsDTO.getDateofBirth(),
                                        userDetailsDTO.getAddress());
            }
        }
        return null;
    }

    public static UserDetailsDTO unwrapUserDetailsDTO(Optional<UserDetailsDTO> entity, Long id) {

        if(entity.isPresent()) return entity.get();
        return null;
        //else throw new RolesNotFoundException(id);
    }
}
