package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BasePetVaccineRequest;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.entity.Vaccine;
import com.cmdotenter.VetCare.repository.PetVaccineRepository;
import com.cmdotenter.VetCare.service.PetService;
import com.cmdotenter.VetCare.service.PetVaccineService;
import com.cmdotenter.VetCare.service.VaccineService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PetVaccineServiceImpl implements PetVaccineService {
    private final PetVaccineRepository petVaccineRepository;
    private final PetService petService;
    private final VaccineService vaccineService;

    public PetVaccineServiceImpl(PetVaccineRepository petVaccineRepository, PetService petService, VaccineService vaccineService) {
        this.petVaccineRepository = petVaccineRepository;
        this.petService = petService;
        this.vaccineService = vaccineService;
    }

    @Transactional
    @Override
    public void save(BasePetVaccineRequest request) {
        PetVaccine petVaccine = new PetVaccine();
        petVaccine.setDate(request.getDate());
        petVaccine.setCount(request.getCount());
        petVaccine.setPet(petService.findById(request.getPetId()));
        petVaccine.setVaccine(vaccineService.findById(request.getVaccineId()));
        petVaccine.setNextDate(request.getNextDate());
        petVaccineRepository.save(petVaccine);

    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<PetVaccine> petVaccine = petVaccineRepository.findById(id);
        PetVaccine thePetVaccine = petVaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
        petVaccineRepository.deleteById(thePetVaccine.getId());
    }

    @Override
    public PetVaccine findById(Long id) {
        Optional<PetVaccine> petVaccine = petVaccineRepository.findById(id);
        return petVaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
    }

    @Override
    public List<PetVaccine> findAll() {
        return petVaccineRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Long id, BasePetVaccineRequest request) {
        Optional<PetVaccine> petVaccine = petVaccineRepository.findById(id);
        PetVaccine thePetVaccine = petVaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
        thePetVaccine.setDate(request.getDate());
        thePetVaccine.setCount(request.getCount());
        thePetVaccine.setPet(petService.findById(request.getPetId()));
        thePetVaccine.setVaccine(vaccineService.findById(request.getVaccineId()));
        thePetVaccine.setNextDate(request.getNextDate());
    }

    @Override
    @Transactional
    public void updateCount(Long id) {
        Optional<PetVaccine> petVaccine = petVaccineRepository.findById(id);
        PetVaccine thePetVaccine = petVaccine.orElseThrow(() -> new RuntimeException("Did not find product id - " + id));
        thePetVaccine.setCount(thePetVaccine.getCount() + 1);
        thePetVaccine.setDate(thePetVaccine.getNextDate());
        thePetVaccine.setNextDate(thePetVaccine.getNextDate().plusDays(thePetVaccine.getVaccine().getPeriodDay()));
    }


    @Override
    public List<PetVaccine> getAllPetVaccinationDetails() {
        List<Object[]> results = petVaccineRepository.findAllFromPetVaccineView();

        List<PetVaccine> petVaccines = new ArrayList<>();
        for (Object[] result : results) {
            PetVaccine petVaccine = new PetVaccine();
            petVaccine.setId(((Number) result[0]).longValue()); // pet_id

            // Convert java.util.Date to java.time.LocalDateTime
            Date date = (Date) result[5];
            LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            petVaccine.setDate(localDateTime); // date

            Date nextDate = (Date) result[6];
            LocalDateTime nextLocalDateTime = Instant.ofEpochMilli(nextDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            petVaccine.setNextDate(nextLocalDateTime); // next_date

            petVaccine.setCount(((Number) result[7]).intValue()); // count
            petVaccine.setPet(petService.findById(((Number) result[1]).longValue())); // pet

            Vaccine vaccine = new Vaccine();
            vaccine.setId(((Number) result[3]).longValue()); // vaccine_id
            vaccine.setDescription(vaccineService.findById(vaccine.getId()).getDescription()); // vaccine_description
            vaccine.setCount(vaccineService.findById(vaccine.getId()).getCount()); // vaccine_count
            vaccine.setPeriodDay(vaccineService.findById(vaccine.getId()).getPeriodDay()); // vaccine_period_day
            vaccine.setName((String) result[4]); // vaccine_name

            petVaccine.setVaccine(vaccine);

            petVaccines.add(petVaccine);
        }

        return petVaccines;
    }

}
