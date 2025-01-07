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

import com.cmdotenter.VetCare.dto.request.BaseOrderRequest;
import com.cmdotenter.VetCare.entity.Order;
import com.cmdotenter.VetCare.service.OrderService;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfOrders() {
        // Arrange
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());
        when(orderService.findAll()).thenReturn(expectedOrders);

        // Act
        ResponseEntity<List<Order>> response = orderController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedOrders, response.getBody());
        verify(orderService).findAll();
    }

    @Test
    void createOrder_ShouldCallServiceSave() {
        // Arrange
        BaseOrderRequest request = new BaseOrderRequest();

        // Act
        ResponseEntity<Void> response = orderController.createOrder(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(orderService).save(request);
    }

    @Test
    void deleteOrder_ShouldCallServiceDeleteById() {
        // Arrange
        Long orderId = 1L;

        // Act
        ResponseEntity<Void> response = orderController.deleteOrder(orderId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(orderService).deleteById(orderId);
    }
} 