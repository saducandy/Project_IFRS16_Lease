package com.example.demo.repository;

import com.example.demo.model.ListOfGeneratedLLChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListOfGeneratedLLChartRepo extends JpaRepository<ListOfGeneratedLLChart, String> {
    ListOfGeneratedLLChart findByLessorName(String lessorName);
}
