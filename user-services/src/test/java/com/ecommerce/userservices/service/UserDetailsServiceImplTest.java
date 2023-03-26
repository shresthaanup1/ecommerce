package com.ecommerce.userservices.service;


import com.ecommerce.userservices.dao.UserDetailsDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.dto.UserDetailsDTO;
import com.ecommerce.userservices.model.AddUserDetailsRequest;
import com.ecommerce.userservices.model.UserDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserDetailsDAO userDetailsDAO;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    @Test
    public void addUserDetailsTest() {
        AddUserDetailsRequest addUserDetailsRequest = new AddUserDetailsRequest(
                "Anup",
                "",
                "Shrestha",
                LocalDate.of(1991, 01, 01),
                "Kathmandu");
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                UUID.randomUUID().toString(),
                addUserDetailsRequest.getFirstName(),
                addUserDetailsRequest.getMiddleName(),
                addUserDetailsRequest.getLastName(),
                addUserDetailsRequest.getDateOfBirth(),
                addUserDetailsRequest.getAddress()
        );

        when(userDetailsDAO.save(any(UserDetailsDTO.class))).thenReturn(userDetailsDTO);

        UserDetails savedUser = userDetailsService.addUserDetails(addUserDetailsRequest);
//        System.out.println(userDetailsDTO.getUserId());
//        System.out.println(savedUser.getUserId());

        assertEquals(userDetailsDTO.getUserId(),savedUser.getUserId());
        assertEquals(userDetailsDTO.getFirstName(),savedUser.getFirstName());
        assertEquals(userDetailsDTO.getMiddleName(),savedUser.getMiddleName());
        assertEquals(userDetailsDTO.getLastName(),savedUser.getLastName());
        assertEquals(userDetailsDTO.getDateofBirth(),savedUser.getDateOfBirth());
        assertEquals(userDetailsDTO.getAddress(),savedUser.getAddress());

        ArgumentCaptor<UserDetailsDTO> userDetailsDTOArgumentCaptor = ArgumentCaptor.forClass(UserDetailsDTO.class);
        verify(userDetailsDAO,times(1)).save(userDetailsDTOArgumentCaptor.capture());
        UserDetailsDTO savedUserDetailsDTO = userDetailsDTOArgumentCaptor.getValue();

        //assertEquals(userDetailsDTO.getUserId(),savedUserDetailsDTO.getUserId()); // this will be different because save function will get different value from the method in service each time.
        assertEquals(addUserDetailsRequest.getFirstName(),savedUserDetailsDTO.getFirstName());
        assertEquals(addUserDetailsRequest.getMiddleName(),savedUserDetailsDTO.getMiddleName());
        assertEquals(addUserDetailsRequest.getLastName(),savedUserDetailsDTO.getLastName());
        assertEquals(userDetailsDTO.getDateofBirth(),savedUserDetailsDTO.getDateofBirth());
        assertEquals(addUserDetailsRequest.getAddress(),savedUserDetailsDTO.getAddress());

        verifyNoMoreInteractions(userDetailsDAO);

    }

    @Test
    public void listUserDetailsTest() {
    }

    @Test
    public void getUserDetailsByIdTest() {
    }

    @Test
    public void deleteUserDetailsByIdTest() {
    }

    @Test
    public void updateUserDetailsTest() {
    }

    @Test
    public void patchUserDetailsTest() {
    }

    @Test
    public void unwrapUserDetailsDTOTest() {
    }
}