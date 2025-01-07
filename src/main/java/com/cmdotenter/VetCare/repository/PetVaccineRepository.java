package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.PetVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PetVaccineRepository extends JpaRepository<PetVaccine, Long> {
}
