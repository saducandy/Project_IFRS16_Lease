package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


    /**********************************************************************************************/
                                    /*Generating setters and getters*/

    public double getTotalIE() {
        return totalIE;
    }

    public void setTotalIE(double totalIE) {
        this.totalIE = totalIE;
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
                '}';
    }
}
