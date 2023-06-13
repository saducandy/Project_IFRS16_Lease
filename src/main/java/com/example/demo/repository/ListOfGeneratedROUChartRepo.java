package com.example.demo.repository;

import com.example.demo.model.ListOfGeneratedROUChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListOfGeneratedROUChartRepo extends JpaRepository<ListOfGeneratedROUChart, String> {

    ListOfGeneratedROUChart findByLessorName(String lessorName);

    ListOfGeneratedROUChart deleteByLessorName(String lessorName);
    ListOfGeneratedROUChart findByRowNumber(int i);

}
