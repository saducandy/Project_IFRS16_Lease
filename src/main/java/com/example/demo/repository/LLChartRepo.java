package com.example.demo.repository;

import com.example.demo.model.LLChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LLChartRepo extends JpaRepository<LLChart, String> {

}
