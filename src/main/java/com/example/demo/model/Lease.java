package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Entity
public class Lease{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leaseID;
    @Column(unique = true)
    private String branchCode;
    private String reportedBy;
    @Column(unique = true)
    private String nameOfLessor;
    private boolean lowValueAsset;
    private boolean shortTermLease;
    private double monthlyAmortizationAmountOfPrepaidLease;
    private double monthlyAmortizationAmountOfUnpaidLease;
    private long contractAgreementPeriod;
    private double initialPayment;
    private double interestRate;
    private long remainingMonthsForPrepaidRentAfterInitialApplication;
    private long remainingMonthsInContractTermNotPaidAfterInitialApplication;
    private String leaseCategoryType;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate prePaymentEndDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate contractExpiryDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate firstInstallmentDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate contractCommencementDate;

    private double area;
    private double paymentPerCare;
    private double vatOrTTO;


    private double totalContractPrice;//calculated

    private double contractVatPayment;//calculated
    private double contractVatPercentage;

    private double totalPeriodPayment;//calculated

    private double annualRentalFee;//calculated

    private long leaseLiabilityPeriod;//calculated

    private double leaseLiability;

    public double getLeaseLiability() {
        return leaseLiability;
    }

    public void setLeaseLiability(double leaseLiability) {
        this.leaseLiability = leaseLiability;
    }

    public long getLeaseLiabilityPeriod() {
        return leaseLiabilityPeriod;
    }

    public void setLeaseLiabilityPeriod(long leaseLiabilityPeriod) {
        this.leaseLiabilityPeriod = leaseLiabilityPeriod;
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

    public long getContractAgreementPeriod() {
        return contractAgreementPeriod;
    }

    public void setContractAgreementPeriod(long contractAgreementPeriod) {
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

    public long getRemainingMonthsForPrepaidRentAfterInitialApplication() {
        return remainingMonthsForPrepaidRentAfterInitialApplication;
    }

    public void setRemainingMonthsForPrepaidRentAfterInitialApplication(long remainingMonthsForPrepaidRentAfterInitialApplication) {
        this.remainingMonthsForPrepaidRentAfterInitialApplication = remainingMonthsForPrepaidRentAfterInitialApplication;
    }

    public long getRemainingMonthsInContractTermNotPaidAfterInitialApplication() {
        return remainingMonthsInContractTermNotPaidAfterInitialApplication;
    }

    public void setRemainingMonthsInContractTermNotPaidAfterInitialApplication(long remainingMonthsInContractTermNotPaidAfterInitialApplication) {
        this.remainingMonthsInContractTermNotPaidAfterInitialApplication = remainingMonthsInContractTermNotPaidAfterInitialApplication;
    }

    public String getLeaseCategoryType() {
        return leaseCategoryType;
    }

    public void setLeaseCategoryType(String leaseCategoryType) {
        this.leaseCategoryType = leaseCategoryType;
    }

    public LocalDate getPrePaymentEndDate() {
        return prePaymentEndDate;
    }

    public void setPrePaymentEndDate(LocalDate prePaymentEndDate) {
        this.prePaymentEndDate = prePaymentEndDate;
    }

    public LocalDate getContractExpiryDate() {
        return contractExpiryDate;
    }

    public void setContractExpiryDate(LocalDate contractExpiryDate) {
        this.contractExpiryDate = contractExpiryDate;
    }

    public LocalDate getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(LocalDate firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public LocalDate getContractCommencementDate() {
        return contractCommencementDate;
    }

    public void setContractCommencementDate(LocalDate contractCommencementDate) {
        this.contractCommencementDate = contractCommencementDate;
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

    public double getAnnualRentalFee() {
        return annualRentalFee;
    }

    public void setAnnualRentalFee(double annualRentalFee) {
        this.annualRentalFee = annualRentalFee;
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
                ", annualRentalFee=" + annualRentalFee +
                '}';
    }
}
