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

import com.cmdotenter.VetCare.dto.request.BaseProductRequest;
import com.cmdotenter.VetCare.entity.Product;
import com.cmdotenter.VetCare.service.ProductService;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfProducts() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(expectedProducts);

        // Act
        ResponseEntity<List<Product>> response = productController.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedProducts, response.getBody());
        verify(productService).findAll();
    }

    @Test
    void createProduct_ShouldCallServiceSave() {
        // Arrange
        BaseProductRequest request = new BaseProductRequest();

        // Act
        ResponseEntity<Void> response = productController.createProduct(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(productService).save(request);
    }

    @Test
    void deleteProduct_ShouldCallServiceDeleteById() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(productService).deleteById(productId);
    }
} 