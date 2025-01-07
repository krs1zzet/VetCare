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

import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.entity.User;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_ShouldPersistPet() {
        // Arrange
        User user = new User();
        user.setEmail("unique_user@example.com");
        user.setPassword("password123");
        user.setName("Test User");
        userRepository.save(user);

        Pet pet = new Pet();
        pet.setSpecies("Dog");
        pet.setUser(user);

        // Act
        Pet savedPet = petRepository.save(pet);

        // Assert
        assertNotNull(savedPet.getId());
        assertEquals("Dog", savedPet.getSpecies());
    }

    @Test
    void findAll_ShouldReturnAllPets() {
        // Arrange
        Pet pet1 = new Pet();
        pet1.setName("Pet 1");
        Pet pet2 = new Pet();
        pet2.setName("Pet 2");
        petRepository.save(pet1);
        petRepository.save(pet2);

        // Act
        List<Pet> pets = petRepository.findAll();

        // Assert
        assertEquals(2, pets.size());
    }

    @Test
    void deleteById_ShouldRemovePet() {
        // Arrange
        Pet pet = new Pet();
        pet.setName("Test Pet");
        Pet savedPet = petRepository.save(pet);

        // Act
        petRepository.deleteById(savedPet.getId());

        // Assert
        Optional<Pet> deletedPet = petRepository.findById(savedPet.getId());
        assertTrue(deletedPet.isEmpty());
    }
} 