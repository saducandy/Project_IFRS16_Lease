package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Entity
public class Lease{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leaseID;

    private String branchCode;
    private String reportedBy;
    private String nameOfLessor;
    private boolean lowValueAsset;
    private boolean shortTermLease;
    private int monthlyAmortizationAmountOfPrepaidLease;
    private int monthlyAmortizationAmountOfUnpaidLease;
    private int contractAgreementPeriod;
    private int initialPayment;
    private double interestRate;
    private int remainingMonthsForPrepaidRentAfterInitialApplication;
    private int remainingMonthsInContractTermNotPaidAfterInitialApplication;
    private String leaseCategoryType;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date prePaymentEndDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date contractExpiryDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date firstInstallmentDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date contractCommencementDate;


    public Long getLease_Id() {
        return leaseID;
    }

    public void setLease_Id(Long lease_Id) {
        leaseID = lease_Id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getNameOfLessor() {
        return nameOfLessor;
    }

    public void setNameOfLessor(String nameOfLessor) {
        this.nameOfLessor = nameOfLessor;
    }

    public boolean isLowValueAsset() {
        return lowValueAsset;
    }

    public void setLowValueAsset(boolean lowValueAsset) {
        this.lowValueAsset = lowValueAsset;
    }

    public boolean isShortTermLease() {
        return shortTermLease;
    }

    public void setShortTermLease(boolean shortTermLease) {
        this.shortTermLease = shortTermLease;
    }

    public int getMonthlyAmortizationAmountOfPrepaidLease() {
        return monthlyAmortizationAmountOfPrepaidLease;
    }

    public void setMonthlyAmortizationAmountOfPrepaidLease(int monthlyAmortizationAmountOfPrepaidLease) {
        this.monthlyAmortizationAmountOfPrepaidLease = monthlyAmortizationAmountOfPrepaidLease;
    }

    public int getMonthlyAmortizationAmountOfUnpaidLease() {
        return monthlyAmortizationAmountOfUnpaidLease;
    }

    public void setMonthlyAmortizationAmountOfUnpaidLease(int monthlyAmortizationAmountOfUnpaidLease) {
        this.monthlyAmortizationAmountOfUnpaidLease = monthlyAmortizationAmountOfUnpaidLease;
    }

    public int getContractAgreementPeriod() {
        return contractAgreementPeriod;
    }

    public void setContractAgreementPeriod(int contractAgreementPeriod) {
        this.contractAgreementPeriod = contractAgreementPeriod;
    }

    public int getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(int initialPayment) {
        this.initialPayment = initialPayment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getRemainingMonthsForPrepaidRentAfterInitialApplication() {
        return remainingMonthsForPrepaidRentAfterInitialApplication;
    }

    public void setRemainingMonthsForPrepaidRentAfterInitialApplication(int remainingMonthsForPrepaidRentAfterInitialApplication) {
        this.remainingMonthsForPrepaidRentAfterInitialApplication = remainingMonthsForPrepaidRentAfterInitialApplication;
    }

    public int getRemainingMonthsInContractTermNotPaidAfterInitialApplication() {
        return remainingMonthsInContractTermNotPaidAfterInitialApplication;
    }

    public void setRemainingMonthsInContractTermNotPaidAfterInitialApplication(int remainingMonthsInContractTermNotPaidAfterInitialApplication) {
        this.remainingMonthsInContractTermNotPaidAfterInitialApplication = remainingMonthsInContractTermNotPaidAfterInitialApplication;
    }

    public String getLeaseCategoryType() {
        return leaseCategoryType;
    }

    public void setLeaseCategoryType(String leaseCategoryType) {
        this.leaseCategoryType = leaseCategoryType;
    }

    public Date getPrePaymentEnd_Date() {
        return prePaymentEndDate;
    }

    public void setPrePaymentEnd_Date(Date prePaymentEnd_Date) {
        prePaymentEndDate = prePaymentEnd_Date;
    }

    public Date getContractExpiryDate() {
        return contractExpiryDate;
    }

    public void setContractExpiryDate(Date contractExpiryDate) {
        this.contractExpiryDate = contractExpiryDate;
    }

    public Date getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(Date firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public Date getContractCommencementDate() {
        return contractCommencementDate;
    }

    public void setContractCommencementDate(Date contractCommencementDate) {
        this.contractCommencementDate = contractCommencementDate;
    }


    @Override
    public String toString() {
        return "Lease{" +
                "leaseID=" + leaseID +
                ", branchCode='" + branchCode + '\'' +
                ", reportedBy='" + reportedBy + '\'' +
                ", nameOfLessor='" + nameOfLessor + '\'' +
                ", lowValueAsset=" + lowValueAsset +
                ", shortTermLease=" + shortTermLease +
                ", monthlyAmortizationAmountOfPrepaidLease=" + monthlyAmortizationAmountOfPrepaidLease +
                ", monthlyAmortizationAmountOfUnpaidLease=" + monthlyAmortizationAmountOfUnpaidLease +
                ", contractAgreementPeriod=" + contractAgreementPeriod +
                ", initialPayment=" + initialPayment +
                ", interestRate=" + interestRate +
                ", remainingMonthsForPrepaidRentAfterInitialApplication=" + remainingMonthsForPrepaidRentAfterInitialApplication +
                ", remainingMonthsInContractTermNotPaidAfterInitialApplication=" + remainingMonthsInContractTermNotPaidAfterInitialApplication +
                ", leaseCategoryType='" + leaseCategoryType + '\'' +
                ", prePaymentEndDate=" + prePaymentEndDate +
                ", contractExpiryDate=" + contractExpiryDate +
                ", firstInstallmentDate=" + firstInstallmentDate +
                ", contractCommencementDate=" + contractCommencementDate +
                '}';
    }
}
