package com.example.demo.repository;

import com.example.demo.entity.UserDAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAORepository extends JpaRepository<UserDAO, Long> {
    Optional<UserDAO> findByUsername(String username);
}
