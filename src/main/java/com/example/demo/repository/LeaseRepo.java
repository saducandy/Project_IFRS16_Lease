package com.example.demo.repository;


import com.example.demo.model.Lease;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeaseRepo extends JpaRepository<Lease, Long> {

    Lease findByBranchCode(String branchcode);
    List<Lease> findByNameOfLessor(String name);

}