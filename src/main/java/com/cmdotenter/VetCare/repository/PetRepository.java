package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT p FROM Pet p WHERE p.user.id = :id")
    List<Pet> findAllByUserId(Long id);


    @Query(value = "SELECT * FROM getpetsbyspecies(:speciesName) AS t(id BIGINT, name VARCHAR, age INT, weight FLOAT)", nativeQuery = true)
    List<Object[]> findPetsBySpecies(@Param("speciesName") String speciesName);

    @Query(value = "SELECT calculatetotalweight(:userId)", nativeQuery = true)
    Double findTotalWeightByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM petvaccines WHERE pet_id = :petId", nativeQuery = true)
    Pet findPetByPetVaccineId(@Param("petId") Long petId);

    @Query(value = "SELECT species FROM pets " +
            "GROUP BY species " +
            "HAVING COUNT(species) > 1", nativeQuery = true)
    List<String> findSpeciesWithMultiplePets();

    @Query(value = "SELECT species FROM pets " +
            "GROUP BY species " +
            "HAVING COUNT(species) > 1 " +
            "INTERSECT " +
            "SELECT species FROM pets " +
            "WHERE species LIKE 'Dog%'",
            nativeQuery = true)
    List<String> findSpeciesWithIntersect();







}
