package com.example.jpa_study.repository;

import com.example.jpa_study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserId(Integer userId);
    Optional<User> findByUsername(String username);
}
