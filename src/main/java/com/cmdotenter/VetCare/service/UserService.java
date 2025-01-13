package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseUserRequest;
import com.cmdotenter.VetCare.dto.request.LoginRequest;
import com.cmdotenter.VetCare.entity.User;

import java.util.List;

public interface UserService {
    void save(BaseUserRequest request);
    User findById(Long id);
    void deleteById(Long id);
    List<User> findAll();
    void update(Long id, BaseUserRequest request);
    User login(LoginRequest request);
    User findUserByPetId(Long petId);
}
