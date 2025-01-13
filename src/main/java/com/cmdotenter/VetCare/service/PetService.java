package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.entity.PetVaccine;
import com.cmdotenter.VetCare.entity.User;

import java.util.List;

public interface PetService {

    void save(BasePetRequest request);

    Pet findById(Long id);

    List<Pet> findAll();

    void deleteById(Long id);

    List<Pet> findAllPetsByUserId(Long id);

    void update(Long id, BasePetRequest request);

    List<Pet> getPetsBySpecies(String species);

    public Double calculateTotalWeight(Integer userId);

    public List<PetVaccine> getVaccinationDetailsByPetId(Integer petId);


    public List<String>findSpeciesWithMultiplePets();

    public List<String> findSpeciesWithIntersect();
}

