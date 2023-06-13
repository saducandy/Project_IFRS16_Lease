package com.example.demo.controller;

import com.example.demo.model.LLChart;
import com.example.demo.model.Lease;
import com.example.demo.model.LeaseLiabilityPV;
import com.example.demo.model.ListOfGeneratedLLChart;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LocalDate finalInstallmentDate1;
    private LocalDate finalInstallmentDate2;


    private LocalDate[] installmentDates = new LocalDate[5];
    private double[] summationIR = new double[20];
    private double[] summationPayment = new double[20];

    private LocalDate endOfMonthForContractCommencementDate;
    private LocalDate endOfMonthForEachMonth;


    private double leaseLiability;
    private double interestExpense;
    private double interestExpense1;
    private int arrayCounter = 0;

    private int rowNumber = 0;
    private double counter = 0;
    private LocalDate eachMonth;
    private double payment;
    private double payment1;
    private LocalDate endOfMonthForContractExDate;


    private long monthsInBetween;

    private double leaseLiabilityPeriod;
    private LeaseLiabilityRepo leaseLiabilityRepo;
    private LLChartRepo llChartRepo;
    private ListOfGeneratedLLChartRepo listOfGeneratedLLChartRepo;
    private ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo;


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



    @Autowired
    public void setListOfGeneratedLLChartRepo(ListOfGeneratedLLChartRepo listOfGeneratedLLChartRepo) {
        this.listOfGeneratedLLChartRepo = listOfGeneratedLLChartRepo;
    }

    @Autowired
    public void setListOfGeneratedROUChartRepo(ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo) {
        this.listOfGeneratedROUChartRepo = listOfGeneratedROUChartRepo;
    }

    /**********************************************************************************************/
    /*Finding the Lease in need of Lease Liability Chart Generation*/
    /**********************************************************************************************/


    public Lease getLeaseFound() {
        return leaseFound;
    }

    public void setLeaseFound(Lease leaseFound) {
        this.leaseFound = leaseFound;
    }







    @RequestMapping(path = "{name}",method = RequestMethod.POST)
    public void generateLLChart(@PathVariable(name ="name") String lessorName) {

//        llChartRepo.deleteAll();

        if (leaseRepo.findByNameOfLessor(lessorName) != null) {


            leaseFound = leaseRepo.findByNameOfLessor(lessorName);


            /*check if this chart is already generated or not*/
            if (listOfGeneratedLLChartRepo.findByLessorName(leaseFound.getNameOfLessor()) == null) {
                ListOfGeneratedLLChart lessorToSave = new ListOfGeneratedLLChart();
                lessorToSave.setLessorName(leaseFound.getNameOfLessor());
                listOfGeneratedLLChartRepo.save(lessorToSave);



            firstInstallmentDate = leaseFound.getFirstInstallmentDate();
            secondInstallmentDate = firstInstallmentDate.plusMonths(12);
            thirdInstallmentDate = secondInstallmentDate.plusMonths(12);
            fourthInstallmentDate = thirdInstallmentDate.plusMonths(12);
            fifthInstallmentDate = fourthInstallmentDate.plusMonths(12);

            leaseLiabilityPeriod = leaseFound.getLeaseLiabilityPeriod();
            endOfMonthForContractExDate = leaseFound.getContractExpiryDate().withDayOfMonth(leaseFound.getContractExpiryDate().getMonth().length(leaseFound.getContractExpiryDate().isLeapYear()));

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
            finalInstallmentDate1 = finalInstallmentDate;
            while (finalInstallmentDate1.getMonth().getValue() != 6) {
                finalInstallmentDate1 = finalInstallmentDate1.plusMonths(1);
            }
            finalInstallmentDate2 = finalInstallmentDate1;
            finalInstallmentDate1 = finalInstallmentDate1.plusMonths(12);/*to make the llchart ending row equal with ROU chart*/
            finalInstallmentDate1 = finalInstallmentDate1.withDayOfMonth(finalInstallmentDate1.getMonth().length(finalInstallmentDate1.isLeapYear()));

            endOfMonthForContractCommencementDate = leaseFound.getContractCommencementDate().withDayOfMonth(leaseFound.getContractCommencementDate().getMonth().length(leaseFound.getContractCommencementDate().isLeapYear()));
            monthsInBetween = ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(), finalInstallmentDate1.plusDays(1));

            eachMonth = endOfMonthForContractCommencementDate;
            /*Finding the lease Liability for the first row*/
            LeaseLiabilityPV leaseLiabilityPV1 = leaseLiabilityRepo.findByLessorName(lessorName);
            leaseLiability = leaseLiabilityPV1.getAmountLeaseLiability();
            Arrays.fill(summationIR, 0);


            for (int i = 0; i < monthsInBetween; i++) {

                rowNumber = rowNumber + 1;
                eachMonth = endOfMonthForContractCommencementDate.plusMonths(i);
                endOfMonthForEachMonth = eachMonth.withDayOfMonth(eachMonth.getMonth().length(eachMonth.isLeapYear()));
                if (eachMonth.isBefore(finalInstallmentDate) || eachMonth.isEqual(finalInstallmentDate)) {

                    counter = counter + 1;

//                System.out.println(endOfMonthForEachMonth.getMonth().getValue());



                    /*calculating Payment*/
                    if (Arrays.asList(installmentDates).contains(endOfMonthForEachMonth)) {
                        payment = leaseFound.getAnnualRentalFee();


                    } else {
                        payment = 0;
                    }


                    /*Calculating Interest expense*/
                    if (endOfMonthForEachMonth.getMonth().getValue() == 6 || Arrays.asList(installmentDates).contains(endOfMonthForEachMonth)) {
                        interestExpense = (leaseLiability * leaseFound.getInterestRate()) * (counter / 12);

                        counter = 0;
                    } else {
                        interestExpense = 0;
                    }


                    /*Calculating payment*/



                    /*Calculating summation Interest Expense*/
                    arrayCounter = arrayCounter + 1;


                    summationPayment[arrayCounter - 1] = payment;
                    summationIR[arrayCounter - 1] = interestExpense;
                    if (endOfMonthForEachMonth.getMonth().getValue() == 6 || finalInstallmentDate.isEqual(endOfMonthForEachMonth)) {
                        interestExpense1 = Arrays.stream(summationIR).sum();
                        Arrays.fill(summationIR, 0);

                        payment1 = Arrays.stream(summationPayment).sum();
                        Arrays.fill(summationPayment, 0);

                        arrayCounter = 0;
                    } else {
                        interestExpense1 = 0;
                        payment1 = 0;
                    }


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
                    generatedLLChart.setTotalIE(interestExpense1);
                    generatedLLChart.setTotalPayment(payment1);
                    generatedLLChart.setBranchCode(leaseFound.getBranchCode());
                    generatedLLChart.setBranchName(leaseFound.getBranchName());
                    generatedLLChart.setBranchDistrict(leaseFound.getBranchDistrict());
                    generatedLLChart.setLessorName(leaseFound.getNameOfLessor());


                    llChartRepo.save(generatedLLChart);
                } else if (eachMonth.isEqual(finalInstallmentDate2)) {

                    System.out.println("Am in");
                    LLChart generatedLLChart2 = new LLChart();
                    generatedLLChart2.setRowNumber(rowNumber);
                    generatedLLChart2.setEachMonth(endOfMonthForEachMonth);
                    generatedLLChart2.setPayment(llChartRepo.findByEachMonthAndLessorName(finalInstallmentDate, leaseFound.getNameOfLessor()).getPayment());
                    generatedLLChart2.setInterestExpense(llChartRepo.findByEachMonthAndLessorName(finalInstallmentDate, leaseFound.getNameOfLessor()).getInterestExpense());
                    generatedLLChart2.setLeaseLiability(llChartRepo.findByEachMonthAndLessorName(finalInstallmentDate, leaseFound.getNameOfLessor()).getLeaseLiability());
                    generatedLLChart2.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                    generatedLLChart2.setTotalIE(llChartRepo.findByEachMonthAndLessorName(finalInstallmentDate, leaseFound.getNameOfLessor()).getTotalIE());
                    generatedLLChart2.setTotalPayment(llChartRepo.findByEachMonthAndLessorName(finalInstallmentDate, leaseFound.getNameOfLessor()).getTotalPayment());
                    generatedLLChart2.setBranchCode(leaseFound.getBranchCode());
                    generatedLLChart2.setBranchName(leaseFound.getBranchName());
                    generatedLLChart2.setBranchDistrict(leaseFound.getBranchDistrict());
                    generatedLLChart2.setLessorName(leaseFound.getNameOfLessor());

                    llChartRepo.save(generatedLLChart2);


                } else {
                    LLChart generatedLLChart1 = new LLChart();
                    generatedLLChart1.setRowNumber(rowNumber);
                    generatedLLChart1.setEachMonth(endOfMonthForEachMonth);
                    generatedLLChart1.setPayment(0);
                    generatedLLChart1.setInterestExpense(0);
                    generatedLLChart1.setLeaseLiability(0);
                    generatedLLChart1.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                    generatedLLChart1.setTotalIE(0);
                    generatedLLChart1.setTotalPayment(0);
                    generatedLLChart1.setBranchCode(leaseFound.getBranchCode());
                    generatedLLChart1.setBranchName(leaseFound.getBranchName());
                    generatedLLChart1.setBranchDistrict(leaseFound.getBranchDistrict());
                    generatedLLChart1.setLessorName(leaseFound.getNameOfLessor());

                    llChartRepo.save(generatedLLChart1);
                }


            }
        }else {
                System.out.println("this LLChart is already generated");
            }    /*ending of the second if, which is used to check the existence of the LLChart */

        /*ending of the first if*/
        }else {
            System.out.println("No lease Found with the given ID");



        }
    }


//    public boolean checkChartExistence(String lessor){
//        /*check if this chart is already generated or not*/
//        if (listOfGeneratedLLChartRepo.findByLessorName(leaseFound.getNameOfLessor()) == null){
//            ListOfGeneratedLLChart lessorToSave = new ListOfGeneratedLLChart();
//            lessorToSave.setLessorName(leaseFound.getNameOfLessor());
//        }
//    }






}











