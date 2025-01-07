package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BasePetRequest;
import com.cmdotenter.VetCare.entity.Pet;

import java.util.List;

public interface PetService {

    void save(BasePetRequest request);
    Pet findById(Long id);
    List<Pet> findAll();
    void deleteById(Long id);

}


