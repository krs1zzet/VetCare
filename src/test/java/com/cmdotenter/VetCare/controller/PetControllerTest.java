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

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.service.PetService;

class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfPets() {
        // Arrange
        List<Pet> expectedPets = Arrays.asList(new Pet(), new Pet());
        when(petService.findAll()).thenReturn(expectedPets);

        // Act
        ResponseEntity<List<Pet>> response = petController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedPets, response.getBody());
        verify(petService).findAll();
    }

    @Test
    void createPet_ShouldCallServiceSave() {
        // Arrange
        BasePetRequest request = new BasePetRequest();

        // Act
        ResponseEntity<Void> response = petController.createPet(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(petService).save(request);
    }

    @Test
    void deletePet_ShouldCallServiceDeleteById() {
        // Arrange
        Long petId = 1L;

        // Act
        ResponseEntity<Void> response = petController.deletePet(petId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(petService).deleteById(petId);
    }
} 