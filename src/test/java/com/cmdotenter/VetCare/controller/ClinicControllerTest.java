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

import com.cmdotenter.VetCare.dto.request.BaseClinicRequest;
import com.cmdotenter.VetCare.entity.Clinic;
import com.cmdotenter.VetCare.service.ClinicService;

class ClinicControllerTest {

    @Mock
    private ClinicService clinicService;

    @InjectMocks
    private ClinicController clinicController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfClinics() {
        // Arrange
        List<Clinic> expectedClinics = Arrays.asList(new Clinic(), new Clinic());
        when(clinicService.findAll()).thenReturn(expectedClinics);

        // Act
        ResponseEntity<List<Clinic>> response = clinicController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedClinics, response.getBody());
        verify(clinicService).findAll();
    }

    @Test
    void createClinic_ShouldCallServiceSave() {
        // Arrange
        BaseClinicRequest request = new BaseClinicRequest();

        // Act
        ResponseEntity<Void> response = clinicController.createClinic(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(clinicService).save(request);
    }

    @Test
    void deleteClinic_ShouldCallServiceDeleteById() {
        // Arrange
        Long clinicId = 1L;

        // Act
        ResponseEntity<Void> response = clinicController.deleteClinic(clinicId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(clinicService).deleteById(clinicId);
    }
} 