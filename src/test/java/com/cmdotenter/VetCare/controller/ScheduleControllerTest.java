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

import com.cmdotenter.VetCare.dto.request.BaseScheduleRequest;
import com.cmdotenter.VetCare.entity.Schedule;
import com.cmdotenter.VetCare.service.ScheduleService;

class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfSchedules() {
        // Arrange
        List<Schedule> expectedSchedules = Arrays.asList(new Schedule(), new Schedule());
        when(scheduleService.findAll()).thenReturn(expectedSchedules);

        // Act
        ResponseEntity<List<Schedule>> response = scheduleController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedSchedules, response.getBody());
        verify(scheduleService).findAll();
    }

    @Test
    void createSchedule_ShouldCallServiceSave() {
        // Arrange
        BaseScheduleRequest request = new BaseScheduleRequest();

        // Act
        ResponseEntity<Void> response = scheduleController.createSchedule(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(scheduleService).save(request);
    }

    @Test
    void deleteSchedule_ShouldCallServiceDeleteById() {
        // Arrange
        Long scheduleId = 1L;

        // Act
        ResponseEntity<Void> response = scheduleController.deleteSchedule(scheduleId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(scheduleService).deleteById(scheduleId);
    }
} 