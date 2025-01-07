package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;
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



}
