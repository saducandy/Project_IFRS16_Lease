package com.example.demo.repository;

import com.example.demo.model.ListOfGeneratedROUChart;
import com.example.demo.model.ROU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ROURepo extends JpaRepository<ROU, String> {

    ROU findByMonths(LocalDate month);
    ROU findByMonthsAndLessorName(LocalDate month, String lessorName);

    ROU findByLessorName(String lessorName);
    ROU deleteAllByLessorName(String lessorName);

    List<ROU> findAllByLessorName(String lessorName);




}
