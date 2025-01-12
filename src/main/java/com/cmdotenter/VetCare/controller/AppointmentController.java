package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.entity.Clinic;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> findAll(){
        List<Appointment> appointmentDTOList = appointmentService.findAll();
        log.info("Find all appointments");
        return ResponseEntity.ok(appointmentDTOList);
    }

    @GetMapping("/appointments/user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<Appointment> appointments = appointmentService.findByUserId(userId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/appointments")
    public ResponseEntity<Void> createAppointment(@RequestBody BaseAppointmentRequest request){
        appointmentService.save(request);
        log.info("Appointment saved");
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteById(id);
        log.info("Appointment deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id, @RequestBody BaseAppointmentRequest request){
        appointmentService.update(id, request);
        log.info("Appointment updated");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/appointments-pet/{id}")
    public ResponseEntity<Pet> getPetByAppointmentId(@PathVariable Long id){
        Pet pet = appointmentService.getPetByAppointmentId(id);
        log.info("Pet found by appointment id");
        return ResponseEntity.ok(pet);
    }
    @PostMapping("/appointments-clinic/{id}")
    public ResponseEntity<Clinic> getClinicByAppointmentId(@PathVariable Long id){
        Clinic clinic = appointmentService.getClinicByAppointmentId(id);
        log.info("Clinic found by appointment id");
        return ResponseEntity.ok(clinic);
    }



}
