package com.example.demo.repository;

import com.example.demo.model.LLChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LLChartRepo extends JpaRepository<LLChart, String> {

    LLChart findByEachMonth(LocalDate month);
    LLChart findByEachMonthAndLessorName(LocalDate month, String lessor);

}
