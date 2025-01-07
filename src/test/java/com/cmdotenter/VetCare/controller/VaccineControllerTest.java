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

import com.cmdotenter.VetCare.dto.request.BaseVaccineRequest;
import com.cmdotenter.VetCare.entity.Vaccine;
import com.cmdotenter.VetCare.service.VaccineService;

class VaccineControllerTest {

    @Mock
    private VaccineService vaccineService;

    @InjectMocks
    private VaccineController vaccineController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfVaccines() {
        // Arrange
        List<Vaccine> expectedVaccines = Arrays.asList(new Vaccine(), new Vaccine());
        when(vaccineService.findAll()).thenReturn(expectedVaccines);

        // Act
        ResponseEntity<List<Vaccine>> response = vaccineController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedVaccines, response.getBody());
        verify(vaccineService).findAll();
    }

    @Test
    void createVaccine_ShouldCallServiceSave() {
        // Arrange
        BaseVaccineRequest request = new BaseVaccineRequest();

        // Act
        ResponseEntity<Void> response = vaccineController.createVaccine(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(vaccineService).save(request);
    }

    @Test
    void deleteVaccine_ShouldCallServiceDeleteById() {
        // Arrange
        Long vaccineId = 1L;

        // Act
        ResponseEntity<Void> response = vaccineController.deleteVaccine(vaccineId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(vaccineService).deleteById(vaccineId);
    }
} 