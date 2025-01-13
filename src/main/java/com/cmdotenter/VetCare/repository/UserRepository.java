package com.cmdotenter.VetCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmdotenter.VetCare.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE id = (SELECT user_id FROM pets WHERE id = :petId)", nativeQuery = true)
    User findUserByPetId(Long petId);
}
