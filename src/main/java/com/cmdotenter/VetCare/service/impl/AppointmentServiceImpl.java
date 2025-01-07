package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.repository.AppointmentRepository;
import com.cmdotenter.VetCare.repository.ClinicRepository;
import com.cmdotenter.VetCare.service.AppointmentService;
import com.cmdotenter.VetCare.service.ClinicService;
import com.cmdotenter.VetCare.service.PetService;
import com.cmdotenter.VetCare.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final ClinicService clinicService;
    private final AppointmentRepository appointmentRepository;
    private final PetService petService;
    private final UserService userService;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ClinicRepository clinicRepository, ClinicService clinicService, PetService petService, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.clinicService = clinicService;
        this.petService = petService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void save(BaseAppointmentRequest request) {
        Appointment appointment = new Appointment();
        appointment.setDate(request.getDate());
        appointment.setClinic(clinicService.findById(request.getClinicId()));
        appointment.setUser(userService.findById(request.getUserId()));
        appointment.setPet(petService.findById(request.getPetId()));
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElseThrow(() -> new RuntimeException("Did not find appointment id - " + id));
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        Appointment theAppointment = appointment.orElseThrow(() -> new RuntimeException("Did not find appointment id - " + id));
        appointmentRepository.deleteById(theAppointment.getId());
    }
}
