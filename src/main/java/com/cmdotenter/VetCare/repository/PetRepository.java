package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT p FROM Pet p WHERE p.user.id = :id")
    List<Pet> findAllByUserId(Long id);
}
