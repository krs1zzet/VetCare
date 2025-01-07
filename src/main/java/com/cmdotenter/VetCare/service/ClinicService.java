package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseAppointmentRequest;
import com.cmdotenter.VetCare.dto.request.BaseClinicRequest;
import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.entity.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClinicService {
    void save(BaseClinicRequest request);
    Clinic findById(Long id);
    List<Clinic> findAll();
    void deleteById(Long id);

}
