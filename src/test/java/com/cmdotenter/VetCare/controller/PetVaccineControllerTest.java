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

import com.cmdotenter.VetCare.dto.request.BasePetVaccineRequest;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.service.PetVaccineService;

class PetVaccineControllerTest {

    @Mock
    private PetVaccineService petVaccineService;

    @InjectMocks
    private PetVaccineController petVaccineController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfPetVaccines() {
        // Arrange
        List<PetVaccine> expectedPetVaccines = Arrays.asList(new PetVaccine(), new PetVaccine());
        when(petVaccineService.findAll()).thenReturn(expectedPetVaccines);

        // Act
        ResponseEntity<List<PetVaccine>> response = petVaccineController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedPetVaccines, response.getBody());
        verify(petVaccineService).findAll();
    }

    @Test
    void createPetVaccine_ShouldCallServiceSave() {
        // Arrange
        BasePetVaccineRequest request = new BasePetVaccineRequest();

        // Act
        ResponseEntity<Void> response = petVaccineController.createPetVaccine(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(petVaccineService).save(request);
    }

    @Test
    void deletePetVaccine_ShouldCallServiceDeleteById() {
        // Arrange
        Long petVaccineId = 1L;

        // Act
        ResponseEntity<Void> response = petVaccineController.deletePetVaccine(petVaccineId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(petVaccineService).deleteById(petVaccineId);
    }
} 