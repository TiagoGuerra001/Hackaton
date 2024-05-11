package com.example.demo.repository;

import com.example.demo.entity.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BodyPartRepository extends JpaRepository<BodyPart,Long> {
}
