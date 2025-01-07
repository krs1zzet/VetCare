package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BasePetVaccineRequest;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.service.PetVaccineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PetVaccineController {
    private final PetVaccineService petVaccineService;

    public PetVaccineController(PetVaccineService petVaccineService) {
        this.petVaccineService = petVaccineService;
    }

    @GetMapping("/pet-vaccines")
    public ResponseEntity<List<PetVaccine>> findAll(){
        List<PetVaccine> petVaccines = petVaccineService.findAll();
        log.info("Find all pet vaccines");
        return ResponseEntity.ok(petVaccines);
    }

    @PostMapping("/pet-vaccines")
    public ResponseEntity<Void> createPetVaccine(@RequestBody BasePetVaccineRequest request){
        petVaccineService.save(request);
        log.info("Pet vaccine saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pet-vaccines/{id}")
    public ResponseEntity<Void> deletePetVaccine(@PathVariable Long id){
        petVaccineService.deleteById(id);
        log.info("Pet vaccine deleted");
        return ResponseEntity.ok().build();
    }
} 