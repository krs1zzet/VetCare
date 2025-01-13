package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.entity.Vaccine;
import com.cmdotenter.VetCare.repository.PetRepository;
import com.cmdotenter.VetCare.repository.PetVaccineRepository;
import com.cmdotenter.VetCare.service.PetService;
import com.cmdotenter.VetCare.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
    private final UserService userService;
    private final PetRepository petRepository;
    private final PetVaccineRepository petVaccineRepository;

    public PetServiceImpl(UserService userService, PetRepository petRepository, PetVaccineRepository petVaccineRepository) {
        this.userService = userService;
        this.petRepository = petRepository;
        this.petVaccineRepository = petVaccineRepository;
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

    public Double calculateTotalWeight(Integer userId) {
        return petRepository.findTotalWeightByUserId(userId);
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

    public List<Pet> getPetsBySpecies(String speciesName) {
        // Fetch results from the repository
        List<Object[]> results = petRepository.findPetsBySpecies(speciesName);

        // Convert Object[] results to Pet objects
        List<Pet> pets = new ArrayList<>();
        for (Object[] result : results) {
            Pet pet = new Pet();
            pet.setId(((Number) result[0]).longValue());  // id
            pet.setName((String) result[1]);             // name
            pet.setAge((Integer) result[2]);             // age
            pet.setWeight(((Number) result[3]).floatValue()); // weight
            pet.setUser(userService.findUserByPetId(((Number) result[0]).longValue()));
            pets.add(pet);
        }

        return pets;
    }

    @Override
    public List<PetVaccine> getVaccinationDetailsByPetId(Integer petId) {
        // Fetch raw results from the database
        List<Object[]> results = petVaccineRepository.findVaccinationDetailsByPetId(petId);

        // Map Object[] results to PetVaccine objects
        List<PetVaccine> petVaccines = new ArrayList<>();
        for (Object[] result : results) {
            // Create a Vaccine object
            Vaccine vaccine = new Vaccine();
            vaccine.setDescription((String) result[0]); // vaccine_description

            // Create a PetVaccine object and associate the Vaccine
            PetVaccine petVaccine = new PetVaccine();
            petVaccine.setVaccine(vaccine);

            // Handle both java.sql.Date and java.sql.Timestamp
            if (result[1] instanceof java.sql.Date) {
                petVaccine.setDate(((java.sql.Date) result[1]).toLocalDate().atStartOfDay());
            } else if (result[1] instanceof java.sql.Timestamp) {
                petVaccine.setDate(((java.sql.Timestamp) result[1]).toLocalDateTime());
            }

            if (result[2] instanceof java.sql.Date) {
                petVaccine.setNextDate(((java.sql.Date) result[2]).toLocalDate().atStartOfDay());
            } else if (result[2] instanceof java.sql.Timestamp) {
                petVaccine.setNextDate(((java.sql.Timestamp) result[2]).toLocalDateTime());
            }

            petVaccines.add(petVaccine);
        }

        return petVaccines;
    }

    @Override
    public List<String> findSpeciesWithMultiplePets() {
        return petRepository.findSpeciesWithMultiplePets();
    }

    @Override
    public List<String> findSpeciesWithIntersect() {
        return petRepository.findSpeciesWithIntersect();
    }


//    @Override
//    public Pet findPetByPetVaccineId(Long petVaccineId) {
//        return petRepository.findPetByPetVaccineId(petVaccineId);
//    }




}
