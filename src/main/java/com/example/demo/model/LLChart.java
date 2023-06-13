package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
public class LLChart {


    /**********************************************************************************************/
                                    /*Defining the Attributes*/
    /**********************************************************************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    private double leaseLiability;
    private double interestExpense;
    private int rowNumber;
    private LocalDate eachMonth;
    private double payment;
    private ZonedDateTime generatedAt;
    private double totalIE;
    private double totalPayment;
    private String branchCode;
    private String branchName;
    private String branchDistrict;
//    @Column(unique = true)
    private String lessorName;


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

    /**********************************************************************************************/
                                    /*Generating setters and getters*/


    public double getTotalIE() {
        return totalIE;
    }

    public void setTotalIE(double totalIE) {
        this.totalIE = totalIE;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    /**********************************************************************************************/



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLeaseLiability() {
        return leaseLiability;
    }

    public void setLeaseLiability(double leaseLiability) {
        this.leaseLiability = leaseLiability;
    }

    public double getInterestExpense() {
        return interestExpense;
    }

    public void setInterestExpense(double interestExpense) {
        this.interestExpense = interestExpense;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public LocalDate getEachMonth() {
        return eachMonth;
    }

    public void setEachMonth(LocalDate eachMonth) {
        this.eachMonth = eachMonth;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(ZonedDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }


    /**********************************************************************************************/
    /*converting each to String*/

    /**********************************************************************************************/
    @Override
    public String toString() {
        return "LLChart{" +
                "id='" + id + '\'' +
                ", leaseLiability=" + leaseLiability +
                ", interestExpense=" + interestExpense +
                ", rowNumber=" + rowNumber +
                ", eachMonth=" + eachMonth +
                ", payment=" + payment +
                ", generatedAt=" + generatedAt +
                ", totalIE=" + totalIE +
                ", totalPayment=" + totalPayment +
                ", branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchDistrict='" + branchDistrict + '\'' +
                ", lessorName='" + lessorName + '\'' +
                '}';
    }
}
