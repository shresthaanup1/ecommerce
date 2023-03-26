package com.ecommerce.userservices.service;

import com.ecommerce.userservices.dao.RolesDAO;
import com.ecommerce.userservices.dto.RolesDTO;
import com.ecommerce.userservices.exception.JsonParameterNotValidException;
import com.ecommerce.userservices.exception.RolesNotFoundException;
import com.ecommerce.userservices.model.AddRolesRequest;
import com.ecommerce.userservices.model.Roles;
import com.ecommerce.userservices.model.UpdateRolesRequest;
import com.ecommerce.userservices.service.RolesServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RolesServiceImplTest {

    @Mock
    private RolesDAO rolesDAO;

    @InjectMocks
    private RolesServiceImpl rolesService;
    @Test
    public void sampleTest(){
        assertEquals(1,1);
    }

    @Test
    public void addRolesTest() {
        AddRolesRequest addRolesRequest = new AddRolesRequest("ADMIN","ADMINISTRATOR");

        RolesDTO rolesDTO = new RolesDTO("ADMIN","ADMINISTRATOR");

        when(rolesDAO.save(any(RolesDTO.class))).thenReturn(rolesDTO);

        Roles role = rolesService.addRoles(addRolesRequest);

        assertEquals(rolesDTO.getRoleName(),role.getRoleName());
        assertEquals(rolesDTO.getRoleDescription(),role.getRoleDescription());

        ArgumentCaptor<RolesDTO> rolesDTOArgumentCaptor = ArgumentCaptor.forClass(RolesDTO.class);
        verify(rolesDAO,times(1)).save(rolesDTOArgumentCaptor.capture());
        RolesDTO savedRolesDTO = rolesDTOArgumentCaptor.getValue();
        assertEquals(addRolesRequest.getRoleName(),savedRolesDTO.getRoleName());
        assertEquals(addRolesRequest.getRoleDescription(),savedRolesDTO.getRoleDescription());

        verifyNoMoreInteractions(rolesDAO);
    }

    @Test
    public void listRolesTest() {
        when(rolesDAO.findAll()).thenReturn(Arrays.asList(
                new RolesDTO("ADMIN","Administrator")
                ,new RolesDTO("USER","User description")
        ));

        List<Roles> roles = rolesService.listRoles();

        assertEquals(2,roles.size());
        assertEquals("ADMIN",roles.get(0).getRoleName());
        assertEquals("User description",roles.get(1).getRoleDescription());
    }

    @Test
    public void getRolesByIdTest() {
        RolesDTO rolesDTO = new RolesDTO("ADMIN","ADMINISTRATOR");

        when(rolesDAO.findById(1L)).thenReturn(Optional.of(rolesDTO));

        Roles roles = rolesService.getRolesById(1L);

        assertEquals(rolesDTO.getRoleName(),roles.getRoleName());
        assertEquals(rolesDTO.getRoleDescription(),roles.getRoleDescription());


    }

    @Test(expected = RolesNotFoundException.class)
    public void getRolesByIdExceptionTest(){
        when(rolesDAO.findById(1L)).thenReturn(Optional.empty());
        //when().thenThrow(RolesNotFoundException.class);
        rolesService.getRolesById(1L);
    }

    @Test
    public void deleteRolesByIdTest() {
        when(rolesDAO.findById(1L)).thenReturn(Optional.of(new RolesDTO("ADMIN","Administrator")));

        rolesService.deleteRolesById(1L);

        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(rolesDAO, times(1)).deleteById(idArgumentCaptor.capture());
        Long deletedId = idArgumentCaptor.getValue();
        assertEquals(1L, deletedId);

        //verifyNoMoreInteractions(rolesDAO); // because we are having interaction twice
    }

    @Test(expected = RolesNotFoundException.class)
    public void deleteRolesByIdExceptionTest() {
        when(rolesDAO.findById(1L)).thenReturn(Optional.empty()); //even if we comment this, test will work

        rolesService.deleteRolesById(1L);
    }
    @Test
    public void updateRolesTest() {
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,"USER","User description");

        when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.of(new RolesDTO("USER","User role")));

        when(rolesDAO.save(any(RolesDTO.class))).thenReturn(new RolesDTO("USER","User description"));

        Roles updatedRole = rolesService.updateRoles(updateRolesRequest);

        assertEquals("USER",updatedRole.getRoleName());
        assertEquals("User description",updatedRole.getRoleDescription());

        ArgumentCaptor<RolesDTO> rolesDTOArgumentCaptor = ArgumentCaptor.forClass(RolesDTO.class);
        verify(rolesDAO,times(1)).save(rolesDTOArgumentCaptor.capture());
        RolesDTO savedRolesDTO = rolesDTOArgumentCaptor.getValue();
        assertEquals(updateRolesRequest.getRoleName(),savedRolesDTO.getRoleName());
        assertEquals(updateRolesRequest.getRoleDescription(),savedRolesDTO.getRoleDescription());

    }

    @Test(expected = RolesNotFoundException.class)
    public void updateRolesRolesNotFoundExceptionTest(){
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,"USER","User description");
        when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.empty());
        rolesService.updateRoles(updateRolesRequest);
    }

    //@Test(expected = JsonParameterNotValidException.class)
    @Test
    public void updateRolesNoIdTest(){
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(null,"USER","User description");
        //when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.empty()); // we donot require this, adding this will create stubbing

        //UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,"USER","User description");
        //when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.of(new RolesDTO("USER","User role")));
        //when(rolesDAO.save(any(RolesDTO.class))).thenReturn(new RolesDTO("USER","User description"));

        assertThrows(JsonParameterNotValidException.class,() -> rolesService.updateRoles(updateRolesRequest),"Not thrown"); // has try catch block, so exception is not thrown
        //rolesService.updateRoles(updateRolesRequest); //if @Test(expected) is used, the method should throw exception
    }

    @Test
    public void patchRolesWithRoleNameNullTest() {
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,null,"User description");
        RolesDTO rolesDTOFromDb = new RolesDTO("USER","Description for user");

        when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.of(rolesDTOFromDb));

        when(rolesDAO.save(any(RolesDTO.class))).thenReturn(new RolesDTO("USER","User description"));

        Roles updatedRole = rolesService.patchRoles(updateRolesRequest);

        assertEquals("USER",updatedRole.getRoleName());
        assertEquals("User description",updatedRole.getRoleDescription());

        ArgumentCaptor<RolesDTO> rolesDTOArgumentCaptor = ArgumentCaptor.forClass(RolesDTO.class);
        verify(rolesDAO,times(1)).save(rolesDTOArgumentCaptor.capture());
        RolesDTO savedRolesDTO = rolesDTOArgumentCaptor.getValue();
        assertEquals(rolesDTOFromDb.getRoleName(),savedRolesDTO.getRoleName());
        assertEquals(updateRolesRequest.getRoleDescription(),savedRolesDTO.getRoleDescription());
    }

    @Test
    public void patchRolesWithRoleDescriptionNullTest() {
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,"USER",null);
        RolesDTO rolesDTOFromDb = new RolesDTO("USERS","Description for user");

        when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.of(rolesDTOFromDb));

        when(rolesDAO.save(any(RolesDTO.class))).thenReturn(new RolesDTO("USER","Description for user"));

        Roles updatedRole = rolesService.patchRoles(updateRolesRequest);

        assertEquals("USER",updatedRole.getRoleName());
        assertEquals("Description for user",updatedRole.getRoleDescription());

        ArgumentCaptor<RolesDTO> rolesDTOArgumentCaptor = ArgumentCaptor.forClass(RolesDTO.class);
        verify(rolesDAO,times(1)).save(rolesDTOArgumentCaptor.capture());
        RolesDTO savedRolesDTO = rolesDTOArgumentCaptor.getValue();
        assertEquals(updateRolesRequest.getRoleName(),savedRolesDTO.getRoleName());
        assertEquals(rolesDTOFromDb.getRoleDescription(),savedRolesDTO.getRoleDescription());
    }

    @Test
    public void patchRolesRolesNotFoundExceptionTest(){

        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(1L,"USER","User description");
        when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.empty());

        assertThrows(RolesNotFoundException.class,() -> rolesService.patchRoles(updateRolesRequest),"Not thrown");

    }

    @Test
    public void patchRolesJsonParameterNotValidExceptionTest(){
        UpdateRolesRequest updateRolesRequest = new UpdateRolesRequest(null,"USER","User description");
        //when(rolesDAO.findById(updateRolesRequest.getId())).thenReturn(Optional.empty());

        assertThrows(JsonParameterNotValidException.class,() -> rolesService.patchRoles(updateRolesRequest),"Not thrown");

    }

    @Test
    public void unwrapRolesDTOTest() {
        RolesDTO rolesDTO = new RolesDTO("User","User description");

        assertEquals(rolesDTO,RolesServiceImpl.unwrapRolesDTO(Optional.of(rolesDTO),1L));
    }

    @Test
    public void unwrapRolesDTOEmptyTest() {
        //RolesDTO rolesDTO = new RolesDTO("User","User description");

        assertThrows(RolesNotFoundException.class
                , () -> RolesServiceImpl.unwrapRolesDTO(Optional.empty(),1L)
                ,"Exception not thrown");
    }

}
