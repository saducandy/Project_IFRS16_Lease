package com.example.demo.controller;

import com.example.demo.model.LLChart;
import com.example.demo.model.Lease;
import com.example.demo.model.LeaseLiabilityPV;
import com.example.demo.repository.LLChartRepo;
import com.example.demo.repository.LeaseLiabilityRepo;
import com.example.demo.repository.LeaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@RestController
@RequestMapping(path = "api/IFRS/LLChart/")
public class LLChartController {


    /**********************************************************************************************/
    /*Defining the Attributes*/
    /**********************************************************************************************/
    private LeaseRepo leaseRepo;
    private Lease leaseFound;

    private String lessorName1;

    private LocalDate firstInstallmentDate;
    private LocalDate secondInstallmentDate;
    private LocalDate thirdInstallmentDate;
    private LocalDate fourthInstallmentDate;

    private LocalDate fifthInstallmentDate;
    private LocalDate sixthInstallmentDate;

    private LocalDate finalInstallmentDate;

    private LocalDate[] installmentDates = new LocalDate[5];
    private LocalDate endOfMonthForContractCommencementDate;
    private LocalDate endOfMonthForEachMonth;


    private double leaseLiability;
    private double interestExpense;
    private double interestExpense1;

    private int rowNumber = 0;
    private LocalDate eachMonth;
    private double payment;


    private long monthsInBetween;

    private long leaseLiabilityPeriod;
    private LeaseLiabilityRepo leaseLiabilityRepo;
    private LLChartRepo llChartRepo;


    /**********************************************************************************************/
    /*Autowiring for further access of the repos*/

    /**********************************************************************************************/

    @Autowired
    public void setLeaseRepo(LeaseRepo leaseRepo) {
        this.leaseRepo = leaseRepo;
    }

    @Autowired
    public void setLeaseLiabilityRepo(LeaseLiabilityRepo leaseLiabilityRepo) {
        this.leaseLiabilityRepo = leaseLiabilityRepo;
    }

    @Autowired
    public void setLlChartRepo(LLChartRepo llChartRepo) {
        this.llChartRepo = llChartRepo;
    }

    /**********************************************************************************************/
    /*Finding the Lease in need of Lease Liability Chart Generation*/

    /**********************************************************************************************/

    @RequestMapping(path = "{name}",method = RequestMethod.POST)
    public void whichLease(@PathVariable(name ="name") String lessorName) {

        llChartRepo.deleteAll();

        if (leaseRepo.findByNameOfLessor(lessorName) != null) {
            leaseFound = leaseRepo.findByNameOfLessor(lessorName);
            firstInstallmentDate = leaseFound.getFirstInstallmentDate();
            secondInstallmentDate = firstInstallmentDate.plusMonths(12);
            thirdInstallmentDate = secondInstallmentDate.plusMonths(12);
            fourthInstallmentDate = thirdInstallmentDate.plusMonths(12);
            fifthInstallmentDate = fourthInstallmentDate.plusMonths(12);

            leaseLiabilityPeriod = leaseFound.getLeaseLiabilityPeriod();

            for (int i = 0; i < leaseLiabilityPeriod; i++) {

                if (i == 0) {
                    installmentDates[i] = firstInstallmentDate;
                } else if (i == 1) {
                    installmentDates[i] = secondInstallmentDate.withDayOfMonth(secondInstallmentDate.getMonth().length(secondInstallmentDate.isLeapYear()));
                } else if (i == 2) {
                    installmentDates[i] = thirdInstallmentDate.withDayOfMonth(thirdInstallmentDate.getMonth().length(thirdInstallmentDate.isLeapYear()));
                } else if (i == 3) {
                    installmentDates[i] = fourthInstallmentDate.withDayOfMonth(fourthInstallmentDate.getMonth().length(fourthInstallmentDate.isLeapYear()));
                } else if (i == 4) {
                    installmentDates[i] = fifthInstallmentDate.withDayOfMonth(fifthInstallmentDate.getMonth().length(fifthInstallmentDate.isLeapYear()));
                }
            }

            finalInstallmentDate = installmentDates[(int) leaseLiabilityPeriod - 1];
            endOfMonthForContractCommencementDate = leaseFound.getContractCommencementDate().withDayOfMonth(leaseFound.getContractCommencementDate().getMonth().length(leaseFound.getContractCommencementDate().isLeapYear()));
            monthsInBetween = ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(), finalInstallmentDate.plusDays(1));

            eachMonth = endOfMonthForContractCommencementDate;
            /*Finding the lease Liability for the first row*/
            LeaseLiabilityPV leaseLiabilityPV1 = leaseLiabilityRepo.findByLessorName(lessorName);
            leaseLiability = leaseLiabilityPV1.getAmountLeaseLiability();


            for (int i = 0; i < monthsInBetween; i++) {
                rowNumber = rowNumber + 1;
                eachMonth = endOfMonthForContractCommencementDate.plusMonths(i);
                endOfMonthForEachMonth = eachMonth.withDayOfMonth(eachMonth.getMonth().length(eachMonth.isLeapYear()));




                /*calculating Payment*/
                if (Arrays.asList(installmentDates).contains(endOfMonthForEachMonth)) {
                    payment = leaseFound.getAnnualRentalFee();

                } else {
                    payment = 0;
                }


                /*Calculating Interest expense*/
                interestExpense = (leaseLiability * leaseFound.getInterestRate()) / 12;

                /*calculating lease Liability for the Chart*/
                leaseLiability = leaseLiability + interestExpense - payment;

                /*generating the lease Liability chart in the repo*/
                LLChart generatedLLChart = new LLChart();
                generatedLLChart.setRowNumber(rowNumber);
                generatedLLChart.setEachMonth(endOfMonthForEachMonth);
                generatedLLChart.setPayment(payment);
                generatedLLChart.setInterestExpense(interestExpense);
                generatedLLChart.setLeaseLiability(leaseLiability);
                generatedLLChart.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));



                llChartRepo.save(generatedLLChart);


            }

        }else {
            System.out.println("No lease Found with the given ID");



        }
    }






}











