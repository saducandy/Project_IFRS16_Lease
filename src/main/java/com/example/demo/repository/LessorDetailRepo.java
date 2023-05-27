package com.example.demo.repository;

import com.example.demo.model.LessorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorDetailRepo extends JpaRepository <LessorDetail, String> {
}
