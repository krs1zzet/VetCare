package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {


    @Query(value = "SELECT v.* FROM vaccines v JOIN pet_vaccines p ON v.id = p.vaccine_id WHERE p.id = :id", nativeQuery = true)
    Vaccine findVaccineByPetVaccinesId(Long id);
}
