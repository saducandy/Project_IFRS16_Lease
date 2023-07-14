package com.example.demo.repository;

import com.example.demo.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, Long> {
}
