package com.example.demo.repository;

import com.example.demo.model.ROU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ROURepo extends JpaRepository<ROU, String> {
}
