package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private double openingROU;
    private double openingLL;
    private double depreciationExpense;
    private double interestExpense;
    private double totalExpense;
    private double disbursement;
    private double closingROU;
    private double closingLL;
    private String branchCode;
    private String branchName;
    private String branchDistrict;
    private String rowLabel;



    /*Getter and Setters*/

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchDistrict() {
        return branchDistrict;
    }

    public void setBranchDistrict(String branchDistrict) {
        this.branchDistrict = branchDistrict;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getOpeningROU() {
        return openingROU;
    }

    public void setOpeningROU(double openingROU) {
        this.openingROU = openingROU;
    }

    public double getOpeningLL() {
        return openingLL;
    }

    public void setOpeningLL(double openingLL) {
        this.openingLL = openingLL;
    }

    public double getDepreciationExpense() {
        return depreciationExpense;
    }

    public void setDepreciationExpense(double depreciationExpense) {
        this.depreciationExpense = depreciationExpense;
    }

    public double getInterestExpense() {
        return interestExpense;
    }

    public void setInterestExpense(double interestExpense) {
        this.interestExpense = interestExpense;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(double disbursement) {
        this.disbursement = disbursement;
    }

    public double getClosingROU() {
        return closingROU;
    }

    public void setClosingROU(double closingROU) {
        this.closingROU = closingROU;
    }

    public double getClosingLL() {
        return closingLL;
    }

    public void setClosingLL(double closingLL) {
        this.closingLL = closingLL;
    }


    /*changing attributes to string*/

    @Override
    public String toString() {
        return "Summary{" +
                "id='" + id + '\'' +
                ", openingROU=" + openingROU +
                ", openingLL=" + openingLL +
                ", depreciationExpense=" + depreciationExpense +
                ", interestExpense=" + interestExpense +
                ", totalExpense=" + totalExpense +
                ", disbursement=" + disbursement +
                ", closingROU=" + closingROU +
                ", closingLL=" + closingLL +
                ", branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchDistrict='" + branchDistrict + '\'' +
                '}';
    }
}
