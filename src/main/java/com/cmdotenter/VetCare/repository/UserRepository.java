package com.cmdotenter.VetCare.repository;

import com.cmdotenter.VetCare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.jar.JarEntry;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
}
