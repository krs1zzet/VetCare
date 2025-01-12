package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.repository.PetRepository;
import com.cmdotenter.VetCare.service.PetService;
import com.cmdotenter.VetCare.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
    private final UserService userService;
    private final PetRepository petRepository;

    public PetServiceImpl( UserService userService, PetRepository petRepository) {
        this.userService = userService;
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    public void save(BasePetRequest request) {
        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setAge(request.getAge());
        pet.setAllergies(request.getAllergies());
        pet.setHeight(request.getHeight());
        pet.setWeight(request.getWeight());
        pet.setIsSterile(request.getIsSterile());
        pet.setSpecies(request.getSpecies());
        pet.setUser(userService.findById(request.getUserId()));
        petRepository.save(pet);

    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElseThrow(() -> new RuntimeException("Did not find pet id - " + id));
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        Pet thePet = pet.orElseThrow(() -> new RuntimeException("Did not find pet id - " + id));
        petRepository.deleteById(thePet.getId());
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VetCare", "postgres", "1234");
             Statement stmt = conn.createStatement()) {
            stmt.execute("LISTEN pet_delete_trigger");
            log.info("Triggered Pet with "+ id +" id has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Transactional
    @Override
    public void update(Long id, BasePetRequest request) {
        Optional<Pet> pet = petRepository.findById(id);
        Pet thePet = pet.orElseThrow(() -> new RuntimeException("Did not find pet id - " + id));
        thePet.setName(request.getName());
        thePet.setAge(request.getAge());
        thePet.setAllergies(request.getAllergies());
        thePet.setHeight(request.getHeight());
        thePet.setWeight(request.getWeight());
        thePet.setIsSterile(request.getIsSterile());
        thePet.setSpecies(request.getSpecies());
        petRepository.save(thePet);
    }

    @Override
    public List<Pet> findAllPetsByUserId(Long id) {
        return petRepository.findAllByUserId(id);
    }
}
