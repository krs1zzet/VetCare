package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    void save(BaseAppointmentRequest request);
    Appointment findById(Long id);
    List<Appointment> findAll();
    void deleteById(Long id);
}
