package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseVaccineRequest;
import com.cmdotenter.VetCare.entity.Vaccine;
import com.cmdotenter.VetCare.repository.VaccineRepository;
import com.cmdotenter.VetCare.service.VaccineService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;


    }
    @Transactional
    @Override
    public void save(BaseVaccineRequest request) {
        Vaccine vaccine = new Vaccine();
        vaccine.setDescription(request.getDescription());
        vaccine.setName(request.getName());
        vaccine.setCount(request.getCount());
        vaccine.setPeriodDay(request.getPeriodDay());
        vaccineRepository.save(vaccine);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Vaccine> vaccine = vaccineRepository.findById(id);
        Vaccine theVaccine = vaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
        vaccineRepository.deleteById(theVaccine.getId());
    }

    @Override
    public Vaccine findById(Long id) {
        Optional<Vaccine> vaccine = vaccineRepository.findById(id);
        return vaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
    }

    @Override
    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }
}
