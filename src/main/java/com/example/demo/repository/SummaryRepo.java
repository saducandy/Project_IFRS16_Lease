package com.example.demo.repository;

import com.example.demo.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepo extends JpaRepository<Summary, String> {

    @Query(value = "SELECT SUM(closingROU) FROM Summary")
    double sumClosingROU();

    @Query(value = "SELECT SUM(openingROU) FROM Summary")
    double sumOpeningROU();

    @Query(value = "SELECT SUM(openingLL) FROM Summary")
    double sumOpeningLL();

    @Query(value = "SELECT SUM(closingLL) FROM Summary")
    double sumClosingLL();

    @Query(value = "SELECT SUM(depreciationExpense) FROM Summary")
    double sumDE();

    @Query(value = "SELECT SUM(interestExpense) FROM Summary")
    double sumIE();

    @Query(value = "SELECT SUM(disbursement) FROM Summary")
    double sumDisbursement();

    @Query(value = "SELECT SUM(totalExpense) FROM Summary")
    double sumTotalEX();




}
