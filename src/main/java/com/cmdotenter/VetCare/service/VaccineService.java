package com.cmdotenter.VetCare.service;


import com.cmdotenter.VetCare.dto.request.BaseVaccineRequest;
import com.cmdotenter.VetCare.entity.Vaccine;

import java.util.List;

public interface VaccineService {
    void save(BaseVaccineRequest request);
    void deleteById(Long id);
    Vaccine findById(Long id);
    List<Vaccine> findAll();
}
