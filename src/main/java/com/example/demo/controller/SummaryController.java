package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/api/IFRS/summary/")
public class SummaryController {


    private LocalDate beginningPeriod;
    private LocalDate closingPeriod;
    private ROURepo rouRepo;
    private LLChartRepo llChartRepo;
    private LeaseRepo leaseRepo;
    private Lease leaseFound;

    private ROU rouFoundForClosing;
    private ROU rouFoundForOpening;

    private String branchCode;
    private LocalDate contractStartDate;
    private LLChart llChartFoundForClosing;
    private LLChart llChartFoundForOpening;
    private SummaryRepo summaryRepo;
    private ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo;
    private double closingLLTotal;
    private double closingROUTotal;
    private double openingLLTotal;
    private double openingROUTotal;
    private double DETotal;
    private double IETotal;
    private double disbursementTotal;
    private double totalExTotal;








    /*Autowiring*/

    @Autowired
    public void setListOfGeneratedROUChartRepo(ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo) {
        this.listOfGeneratedROUChartRepo = listOfGeneratedROUChartRepo;
    }

    @Autowired
    public void setSummaryRepo(SummaryRepo summaryRepo) {
        this.summaryRepo = summaryRepo;
    }

    @Autowired
    public void setLeaseRepo(LeaseRepo leaseRepo) {
        this.leaseRepo = leaseRepo;
    }

    @Autowired
    public void setRouRepo(ROURepo rouRepo) {
        this.rouRepo = rouRepo;
    }

    @Autowired
    public void setLlChartRepo(LLChartRepo llChartRepo) {
        this.llChartRepo = llChartRepo;
    }



    @RequestMapping(path = "{LocalDate}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void summaryReport(@PathVariable(name = "LocalDate") LocalDate reportingPeriod) {


        /*Start By clearing the DB*/
        summaryRepo.deleteAll();

        /*Finding Beginning and Closing Period for the Summary Report*/

        closingPeriod = reportingPeriod;
        while (closingPeriod.getMonth().getValue() != 6) {
            closingPeriod = closingPeriod.plusMonths(1);

        }

        closingPeriod = getEOM(closingPeriod);
        /*wed@eyu0921*/

        beginningPeriod = closingPeriod.minusMonths(11);
        beginningPeriod = beginningPeriod.withDayOfMonth(beginningPeriod.getMonth().length(beginningPeriod.isLeapYear()));
        /***Creating an array for storing all the branches in the ROU list table***/

        int i;
        i = (int) listOfGeneratedROUChartRepo.count();


        String[] lessorROUlist = new String[i];

        for (int j = 0; j < i; j++) {
            lessorROUlist[j] = listOfGeneratedROUChartRepo.findByRowNumber(j + 1).getLessorName();
        }

        System.out.println(Arrays.toString(lessorROUlist));

        /***********************************************************/

        for (int k = 0; k < i; k++){

            if (rouRepo.findByMonthsAndLessorName(closingPeriod, lessorROUlist[k]) != null) {

                llChartFoundForClosing = llChartRepo.findByEachMonthAndLessorName(closingPeriod, lessorROUlist[k]);
                rouFoundForClosing = rouRepo.findByMonthsAndLessorName(closingPeriod, lessorROUlist[k]);
                branchCode = rouFoundForClosing.getBranchCode();
                leaseFound = leaseRepo.findByBranchCode(branchCode);
                contractStartDate = leaseFound.getContractCommencementDate();
                contractStartDate = contractStartDate.withDayOfMonth(contractStartDate.getMonth().length(contractStartDate.isLeapYear()));
                if (beginningPeriod.isBefore(contractStartDate)) {
                    beginningPeriod = contractStartDate;
                }
                llChartFoundForOpening = llChartRepo.findByEachMonthAndLessorName(beginningPeriod,lessorROUlist[k]);
                rouFoundForOpening = rouRepo.findByMonthsAndLessorName(beginningPeriod, lessorROUlist[k]);


                /*Finding openingROU*/
                Summary newSummary = new Summary();
                newSummary.setOpeningROU(rouFoundForOpening.getRightOUOpening());
                newSummary.setOpeningLL(llChartFoundForOpening.getLeaseLiability());
                newSummary.setDepreciationExpense(rouFoundForClosing.getTotalDepreciationExpense());
                newSummary.setInterestExpense(llChartFoundForClosing.getTotalIE());
                newSummary.setTotalExpense(rouFoundForClosing.getTotalDepreciationExpense() + llChartFoundForClosing.getTotalIE());
                newSummary.setDisbursement(llChartFoundForClosing.getTotalPayment());
                newSummary.setClosingROU(rouFoundForClosing.getRightOUClosing());
                newSummary.setClosingLL(llChartFoundForClosing.getLeaseLiability());
                newSummary.setBranchCode(rouFoundForClosing.getBranchCode());
                newSummary.setBranchName(rouFoundForClosing.getBranchName());
                newSummary.setBranchDistrict(rouFoundForClosing.getBranchDistrict());
                newSummary.setRowLabel("Monthly-Data");

                summaryRepo.save(newSummary);



            } else {
                System.out.println("you entered date which is out of bound!");
            }

    }
        summaryTotal();


        }


        public LocalDate getEOM(LocalDate month){

            return month.withDayOfMonth(month.getMonth().length(month.isLeapYear()));

        }


        public void summaryTotal(){

            closingROUTotal = summaryRepo.sumClosingROU();
            openingROUTotal = summaryRepo.sumOpeningROU();
            openingLLTotal = summaryRepo.sumOpeningLL();
            closingLLTotal = summaryRepo.sumClosingLL();
            DETotal = summaryRepo.sumDE();
            IETotal = summaryRepo.sumIE();
            disbursementTotal = summaryRepo.sumDisbursement();
            totalExTotal = summaryRepo.sumTotalEX();

            System.out.println(closingROUTotal +","+ openingLLTotal+","+openingROUTotal+","+closingLLTotal+","+DETotal +","+ IETotal +","+ disbursementTotal+","+totalExTotal);


            Summary newSummaryTotal = new Summary();
            newSummaryTotal.setClosingROU(closingROUTotal);
            newSummaryTotal.setOpeningROU(openingROUTotal);
            newSummaryTotal.setOpeningLL(openingLLTotal);
            newSummaryTotal.setClosingLL(closingLLTotal);
            newSummaryTotal.setDepreciationExpense(DETotal);
            newSummaryTotal.setInterestExpense(IETotal);
            newSummaryTotal.setDisbursement(disbursementTotal);
            newSummaryTotal.setTotalExpense(totalExTotal);
            newSummaryTotal.setBranchCode("SUM-All branch-codes");
            newSummaryTotal.setBranchName("Sum-All branch-names");
            newSummaryTotal.setBranchDistrict("Sum-All branch-Districts");
            newSummaryTotal.setRowLabel("TOTAL");

            summaryRepo.save(newSummaryTotal);

        }




    }

