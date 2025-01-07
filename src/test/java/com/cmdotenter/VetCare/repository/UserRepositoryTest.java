package com.cmdotenter.VetCare.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.cmdotenter.VetCare.entity.User;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_ShouldPersistUser() {
        // Arrange
        User user = new User();
        user.setEmail("test@example23.com");
        user.setPassword("password123");
        user.setName("Test User");

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser.getId());
        assertEquals("test@example23.com", savedUser.getEmail());
        assertEquals("Test User", savedUser.getName());
        assertEquals("password123", savedUser.getPassword());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setEmail("user1@example2345.com");
        user1.setName("User 1");
        user1.setPassword("password123");
        User user2 = new User();
        user2.setEmail("user2@example23678.com");
        user2.setName("User 2");
        user2.setPassword("password123");
        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<User> users = userRepository.findAll();

        // Assert
        assertEquals(8, users.size());
    }

    @Test
    void deleteById_ShouldRemoveUser() {
        // Arrange
        User user = new User();
        user.setName("Test User");
        User savedUser = userRepository.save(user);

        // Act
        userRepository.deleteById(savedUser.getId());

        // Assert
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertTrue(deletedUser.isEmpty());
    }
} 