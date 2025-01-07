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

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.service.AppointmentService;

class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfAppointments() {
        // Arrange
        List<Appointment> expectedAppointments = Arrays.asList(new Appointment(), new Appointment());
        when(appointmentService.findAll()).thenReturn(expectedAppointments);

        // Act
        ResponseEntity<List<Appointment>> response = appointmentController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedAppointments, response.getBody());
        verify(appointmentService).findAll();
    }

    @Test
    void createAppointment_ShouldCallServiceSave() {
        // Arrange
        BaseAppointmentRequest request = new BaseAppointmentRequest();

        // Act
        ResponseEntity<Void> response = appointmentController.createAppointment(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(appointmentService).save(request);
    }

    @Test
    void deleteAppointment_ShouldCallServiceDeleteById() {
        // Arrange
        Long appointmentId = 1L;

        // Act
        ResponseEntity<Void> response = appointmentController.deleteAppointment(appointmentId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(appointmentService).deleteById(appointmentId);
    }
} 