package com.cmdotenter.VetCare.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.cmdotenter.VetCare.dto.request.BaseUserRequest;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.service.UserService;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfUsers() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userService.findAll()).thenReturn(expectedUsers);

        // Act
        ResponseEntity<List<User>> response = userController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedUsers, response.getBody());
        verify(userService).findAll();
    }

    @Test
    void createUser_ShouldCallServiceSave() {
        // Arrange
        BaseUserRequest request = new BaseUserRequest();

        // Act
        ResponseEntity<Void> response = userController.createUser(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(userService).save(request);
    }

    @Test
    void deleteUser_ShouldCallServiceDeleteById() {
        // Arrange
        Long userId = 1L;

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(userService).deleteById(userId);
    }
} 