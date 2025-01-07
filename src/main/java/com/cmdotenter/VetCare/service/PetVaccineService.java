package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BasePetVaccineRequest;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.entity.Vaccine;

import java.util.List;

public interface PetVaccineService {
    void save(BasePetVaccineRequest request);
    void deleteById(Long id);
    PetVaccine findById(Long id);
    List<PetVaccine> findAll();
}
