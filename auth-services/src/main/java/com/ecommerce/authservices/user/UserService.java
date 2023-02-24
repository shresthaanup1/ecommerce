package com.ecommerce.authservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User addUser(User userRequest) {
        UserDTO userDTO = new UserDTO(userRequest.getUserName(), userRequest.getUserEmail());
        userDTO = userDAO.save(userDTO);
        return new User(userDTO.getId(), userDTO.getUserName(), userDTO.getUserEmail());
    }

    public List<User> listUsers() {
        List<UserDTO> userDTOs = userDAO.findAll();
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(new User(userDTO.getId(),userDTO.getUserName(),userDTO.getUserEmail()));
        }
        return users;
    }

    public User getUserById(Long id) {
        UserDTO userDTO = userDAO.findById(id).get();
        return new User(userDTO.getId(),userDTO.getUserName(),userDTO.getUserEmail());
    }

    public User getUserByUserName(String name) {
        UserDTO userDTO = userDAO.findById(1L).get();
        return new User(userDTO.getId(),userDTO.getUserName(),userDTO.getUserEmail());
    }
}
