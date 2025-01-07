package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseClinicRequest;
import com.cmdotenter.VetCare.entity.Clinic;
import com.cmdotenter.VetCare.service.ClinicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClinicController {
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/clinics")
    public ResponseEntity<List<Clinic>> findAll(){
        List<Clinic> clinics = clinicService.findAll();
        log.info("Find all clinics");
        return ResponseEntity.ok(clinics);
    }

    @PostMapping("/clinics")
    public ResponseEntity<Void> createClinic(@RequestBody BaseClinicRequest request){
        clinicService.save(request);
        log.info("Clinic saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clinics/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id){
        clinicService.deleteById(id);
        log.info("Clinic deleted");
        return ResponseEntity.ok().build();
    }
} 