package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseVaccineRequest;
import com.cmdotenter.VetCare.entity.Vaccine;
import com.cmdotenter.VetCare.service.VaccineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/vaccines")
    public ResponseEntity<List<Vaccine>> findAll(){
        List<Vaccine> vaccines = vaccineService.findAll();
        log.info("Find all vaccines");
        return ResponseEntity.ok(vaccines);
    }

    @PostMapping("/vaccines")
    public ResponseEntity<Void> createVaccine(@RequestBody BaseVaccineRequest request){
        vaccineService.save(request);
        log.info("Vaccine saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/vaccines/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id){
        vaccineService.deleteById(id);
        log.info("Vaccine deleted");
        return ResponseEntity.ok().build();
    }
} 