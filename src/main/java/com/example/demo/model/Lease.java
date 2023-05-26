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
    private double monthlyAmortizationAmountOfPrepaidLease;
    private double monthlyAmortizationAmountOfUnpaidLease;
    private int contractAgreementPeriod;
    private double initialPayment;
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

    private double area;
    private double paymentPerCare;
    private double vatOrTTO;


    private double totalContractPrice;//calculated

    private double contractVatPayment;//calculated
    private double contractVatPercentage;

    private double totalPeriodPayment;//calculated

    private double annualRentalFee;//calculated

    public double getAnnualRentalFee() {
        return annualRentalFee;
    }

    public void setAnnualRentalFee(double annualRentalFee) {
        this.annualRentalFee = annualRentalFee;
    }

    public double getTotalContractPrice() {
        return totalContractPrice;
    }

    public void setTotalContractPrice(double totalContractPrice) {
        this.totalContractPrice = totalContractPrice;
    }

    public double getContractVatPayment() {
        return contractVatPayment;
    }

    public void setContractVatPayment(double contractVatPayment) {
        this.contractVatPayment = contractVatPayment;
    }

    public double getContractVatPercentage() {
        return contractVatPercentage;
    }

    public void setContractVatPercentage(double contractVatPercentage) {
        this.contractVatPercentage = contractVatPercentage;
    }

    public double getTotalPeriodPayment() {
        return totalPeriodPayment;
    }

    public void setTotalPeriodPayment(double totalPeriodPayment) {
        this.totalPeriodPayment = totalPeriodPayment;
    }




    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPaymentPerCare() {
        return paymentPerCare;
    }

    public void setPaymentPerCare(double paymentPerCare) {
        this.paymentPerCare = paymentPerCare;
    }

    public double getVatOrTTO() {
        return vatOrTTO;
    }

    public void setVatOrTTO(double vatOrTTO) {
        this.vatOrTTO = vatOrTTO;
    }

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

    public double getMonthlyAmortizationAmountOfPrepaidLease() {
        return monthlyAmortizationAmountOfPrepaidLease;
    }



    public void setMonthlyAmortizationAmountOfPrepaidLease(double monthlyAmortizationAmountOfPrepaidLease) {
        this.monthlyAmortizationAmountOfPrepaidLease = monthlyAmortizationAmountOfPrepaidLease;
    }


    public double getMonthlyAmortizationAmountOfUnpaidLease() {
        return monthlyAmortizationAmountOfUnpaidLease;
    }

    public void setMonthlyAmortizationAmountOfUnpaidLease(double monthlyAmortizationAmountOfUnpaidLease) {
        this.monthlyAmortizationAmountOfUnpaidLease = monthlyAmortizationAmountOfUnpaidLease;
    }

    public int getContractAgreementPeriod() {
        return contractAgreementPeriod;
    }

    public void setContractAgreementPeriod(int contractAgreementPeriod) {
        this.contractAgreementPeriod = contractAgreementPeriod;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(double initialPayment) {
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
                ", area=" + area +
                ", paymentPerCare=" + paymentPerCare +
                ", vatOrTTO=" + vatOrTTO +
                ", totalContractPrice=" + totalContractPrice +
                ", contractVatPayment=" + contractVatPayment +
                ", contractVatPercentage=" + contractVatPercentage +
                ", totalPeriodPayment=" + totalPeriodPayment +
                '}';
    }
}
