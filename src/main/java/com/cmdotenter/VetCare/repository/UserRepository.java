package com.cmdotenter.VetCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmdotenter.VetCare.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
}
