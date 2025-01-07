package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseClinicRequest;
import com.cmdotenter.VetCare.entity.Clinic;
import com.cmdotenter.VetCare.repository.ClinicRepository;
import com.cmdotenter.VetCare.service.ClinicService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Transactional
    @Override
    public void save(BaseClinicRequest request) {
        Clinic clinic = new Clinic();
        clinic.setName(request.getName());
        clinic.setLocation(request.getLocation());
        clinic.setPhone(request.getNumber());
        clinicRepository.save(clinic);
    }

    @Override
    public Clinic findById(Long id) {
        Optional<Clinic> clinic = clinicRepository.findById(id);
        return clinic.orElseThrow(() -> new RuntimeException("Did not find clinic id - " + id));
    }

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    @Transactional
    @Override
    public void  deleteById(Long id) {
        Optional<Clinic> clinic = clinicRepository.findById(id);
        Clinic theClinic = clinic.orElseThrow(() -> new RuntimeException("Did not find clinic id - " + id));
        clinicRepository.deleteById(theClinic.getId());
    }
}
