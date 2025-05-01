package com.techmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techmanage.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}