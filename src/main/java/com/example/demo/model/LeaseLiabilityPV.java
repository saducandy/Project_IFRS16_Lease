package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;

@Entity
public class LeaseLiabilityPV {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String lessorName;
    private String branchCode;
    private double amountLeaseLiability;
    private ZonedDateTime calculatedAt;

    public ZonedDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(ZonedDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }

    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public double getAmountLeaseLiability() {
        return amountLeaseLiability;
    }

    public void setAmountLeaseLiability(double amountLeaseLiability) {
        this.amountLeaseLiability = amountLeaseLiability;
    }




    @Override
    public String toString() {
        return "LeaseLiabilityPV{" +
                "id='" + id + '\'' +
                ", lessorName='" + lessorName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", amountLeaseLiability='" + amountLeaseLiability + '\'' +
                '}';
    }
}
