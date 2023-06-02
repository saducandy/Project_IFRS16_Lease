package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class ROU {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = true)

    private double rightOUOpening;
    @Column(nullable = true)
    private double rightOUClosing;
    private double depreciationOfROU;
    private String lessorName;
    private String BranchCode;
    private ZonedDateTime generatedAt;
    private LocalDate months;
    private double totalDepreciationExpense;

    public double getTotalDepreciationExpense() {
        return totalDepreciationExpense;
    }

    public void setTotalDepreciationExpense(double totalDepreciationExpense) {
        this.totalDepreciationExpense = totalDepreciationExpense;
    }

    public LocalDate getMonths() {
        return months;
    }

    public void setMonths(LocalDate months) {
        this.months = months;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRightOUOpening() {
        return rightOUOpening;
    }

    public void setRightOUOpening(double rightOUOpening) {
        this.rightOUOpening = rightOUOpening;
    }

    public double getRightOUClosing() {
        return rightOUClosing;
    }

    public void setRightOUClosing(double rightOUClosing) {
        this.rightOUClosing = rightOUClosing;
    }

    public double getDepreciationOfROU() {
        return depreciationOfROU;
    }

    public void setDepreciationOfROU(double depreciationOfROU) {
        this.depreciationOfROU = depreciationOfROU;
    }

    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(ZonedDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }


    @Override
    public String toString() {
        return "ROU{" +
                "id='" + id + '\'' +
                ", rightOUOpening=" + rightOUOpening +
                ", rightOUClosing=" + rightOUClosing +
                ", depreciationOfROU=" + depreciationOfROU +
                ", lessorName='" + lessorName + '\'' +
                ", BranchCode='" + BranchCode + '\'' +
                ", generatedAt=" + generatedAt +
                ", months=" + months +
                '}';
    }
}

