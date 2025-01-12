package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.entity.Clinic;
import com.cmdotenter.VetCare.entity.Pet;

import java.util.List;

public interface AppointmentService {
    void save(BaseAppointmentRequest request);
    Appointment findById(Long id);
    List<Appointment> findAll();
    void deleteById(Long id);
    void update(Long id, BaseAppointmentRequest request);
    Pet getPetByAppointmentId(Long id);
    Clinic getClinicByAppointmentId(Long id);
    List<Appointment> findByUserId(Long userId);
}
