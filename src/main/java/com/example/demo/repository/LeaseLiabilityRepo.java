package com.example.demo.repository;

import com.example.demo.model.LeaseLiabilityPV;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseLiabilityRepo extends JpaRepository<LeaseLiabilityPV, String> {

    LeaseLiabilityPV findByLessorName(String name);
    List<LeaseLiabilityPV> findByOrderByCalculatedAtDesc();
    @Transactional
    List<LeaseLiabilityPV> deleteByOrderByCalculatedAtDesc();

}
