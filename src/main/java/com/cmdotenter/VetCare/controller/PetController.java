package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
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
}
