package com.cmdotenter.VetCare.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.cmdotenter.VetCare.entity.Product;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_ShouldPersistProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10f); // Use float for consistency

        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10f, savedProduct.getPrice(), 0.01); // Use delta for float comparison

        // Verify that the product is saved
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size()); // Ensure only one product is saved
        assertEquals(savedProduct.getId(), products.get(0).getId()); // Check if the saved product is the same
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setName("Product 2");
        productRepository.save(product1);
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertEquals(2, products.size());
    }

    @Test
    void deleteById_ShouldRemoveProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");
        Product savedProduct = productRepository.save(product);

        // Act
        productRepository.deleteById(savedProduct.getId());

        // Assert
        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertTrue(deletedProduct.isEmpty());
    }
} 