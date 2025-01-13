package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> findAll(){
        List<Pet> petDTOList = petService.findAll();
        log.info("Find all pets");
        return ResponseEntity.ok(petDTOList);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<List<Pet>> findAllPetsByUserId(@PathVariable Long id){
        List<Pet> pet = petService.findAllPetsByUserId(id);
        log.info("Find all pets by user id");
        return ResponseEntity.ok(pet);
    }

    @PostMapping("/pets")
    public ResponseEntity<Void> createPet(@RequestBody BasePetRequest request){
        petService.save(request);
        log.info("Pet saved");
        return  ResponseEntity.ok().build();
    }
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id){
        petService.deleteById(id);
        log.info("Pet deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Void> updatePet(@PathVariable Long id, @RequestBody BasePetRequest request){
        petService.update(id, request);
        log.info("Pet updated");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pets/species/{species}")
    public ResponseEntity<List<Pet>> findAllBySpecies(@PathVariable String species){
        List<Pet> pets = petService.getPetsBySpecies(species);
        log.info("Find all pets by species");
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/pets/calculate-weight/{id}")
    public ResponseEntity<Double> calculateWeight(@PathVariable Long id){
        Double weight = petService.calculateTotalWeight(id.intValue());
        log.info("Weight calculated");
        return ResponseEntity.ok(weight);
    }
    @PostMapping("/pets/vaccination-details/{id}")
    public ResponseEntity<List<PetVaccine>> getVaccinationDetailsByPetId(@PathVariable Long id){
        List<PetVaccine> vaccination = petService.getVaccinationDetailsByPetId(id.intValue());
        log.info("Vaccination details found by pet id");
        return ResponseEntity.ok(vaccination);
    }

    @GetMapping("/pets/species-count")
    public ResponseEntity<List<String>> getSpeciesCount(){
        List<String> speciesName = petService.findSpeciesWithMultiplePets();
        log.info("Species count");
        return ResponseEntity.ok(speciesName);
    }

    @GetMapping("/pets/species-count/intersect")
    public ResponseEntity<List<String>> getSpeciesCountIntersect(){
        List<String> speciesName = petService.findSpeciesWithIntersect();
        log.info("Species count");
        return ResponseEntity.ok(speciesName);
    }
}
