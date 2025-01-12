package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.PetVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PetVaccineRepository extends JpaRepository<PetVaccine, Long> {
    @Query(value = "(SELECT * FROM pet_vaccines WHERE pet_id = :petId1) " +
            "UNION " +
            "(SELECT * FROM pet_vaccines WHERE pet_id = :petId2)", nativeQuery = true)
    List<PetVaccine> findUnionByPetIds(@Param("petId1") Long petId1, @Param("petId2") Long petId2);

    @Query(value = "SELECT * FROM pet_vaccine", nativeQuery = true)
    List<PetVaccine> findAllPetVaccineViews();


}
