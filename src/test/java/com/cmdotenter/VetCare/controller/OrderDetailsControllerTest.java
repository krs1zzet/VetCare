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

import com.cmdotenter.VetCare.dto.request.BaseOrderDetailsRequest;
import com.cmdotenter.VetCare.entity.OrderDetails;
import com.cmdotenter.VetCare.service.OrderDetailsService;

class OrderDetailsControllerTest {

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private OrderDetailsController orderDetailsController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfOrderDetails() {
        // Arrange
        List<OrderDetails> expectedOrderDetails = Arrays.asList(new OrderDetails(), new OrderDetails());
        when(orderDetailsService.findAll()).thenReturn(expectedOrderDetails);

        // Act
        ResponseEntity<List<OrderDetails>> response = orderDetailsController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedOrderDetails, response.getBody());
        verify(orderDetailsService).findAll();
    }

    @Test
    void createOrderDetails_ShouldCallServiceSave() {
        // Arrange
        BaseOrderDetailsRequest request = new BaseOrderDetailsRequest();

        // Act
        ResponseEntity<Void> response = orderDetailsController.createOrderDetails(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(orderDetailsService).save(request);
    }

    @Test
    void deleteOrderDetails_ShouldCallServiceDeleteById() {
        // Arrange
        Long orderDetailsId = 1L;

        // Act
        ResponseEntity<Void> response = orderDetailsController.deleteOrderDetails(orderDetailsId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(orderDetailsService).deleteById(orderDetailsId);
    }
} 