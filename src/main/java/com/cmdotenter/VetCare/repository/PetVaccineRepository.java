package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.PetVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PetVaccineRepository extends JpaRepository<PetVaccine, Long> {

    @Query(value = "SELECT * FROM public.pet_vaccine_view", nativeQuery = true)
    List<Object[]> findAllFromPetVaccineView();

    @Query(value = "SELECT vaccine_description, vaccine_date, next_date FROM getvaccinationdetails(:petId)", nativeQuery = true)
    List<Object[]> findVaccinationDetailsByPetId(@Param("petId") Integer petId);


}
