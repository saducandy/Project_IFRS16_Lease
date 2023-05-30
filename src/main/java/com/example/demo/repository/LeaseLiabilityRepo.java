package com.example.demo.repository;

import com.example.demo.model.LeaseLiabilityPV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseLiabilityRepo extends JpaRepository<LeaseLiabilityPV, String> {

    LeaseLiabilityPV findByLessorName(String name);

}
