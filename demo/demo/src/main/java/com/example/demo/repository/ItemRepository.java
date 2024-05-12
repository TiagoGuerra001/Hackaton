package com.example.demo.repository;
import com.example.demo.entity.Item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByBoughtFalse();
}
