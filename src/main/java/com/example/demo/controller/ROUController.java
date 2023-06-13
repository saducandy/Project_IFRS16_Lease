package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.model.ListOfGeneratedROUChart;
import com.example.demo.model.ROU;
import com.example.demo.repository.LeaseRepo;
import com.example.demo.repository.ListOfGeneratedROUChartRepo;
import com.example.demo.repository.ROURepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(path = "/api/IFRS/ROU/")
public class ROUController {

    /*Attributes used in this controller*/
    private LeaseRepo leaseRepo;
    private ROURepo rouRepo;
    private Lease leaseFound;
    private long monthsInBetween;
    private long monthsInBetween1;

    private LocalDate endOfMonthForContractExDate;
    private double openingROU;
    private double closingROU;
    private double depreciationROU;
    private String lessorName1;
    private String branchCode;
    private LocalDate eachMonth;
    private LocalDate endOfMonthForContractCommencementDate;
    private LocalDate endOfMonthForContractExDate1;
    private LocalDate endOfMonthForContractExDate2;
    private ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo;


    private int counter = 0;


    /*Autowiring the Repository classes*/

    @Autowired
    public void setListOfGeneratedROUChartRepo(ListOfGeneratedROUChartRepo listOfGeneratedROUChartRepo) {
        this.listOfGeneratedROUChartRepo = listOfGeneratedROUChartRepo;
    }

    @Autowired
    public void setRouRepo(ROURepo rouRepo) {
        this.rouRepo = rouRepo;
    }

    @Autowired
    public void setLeaseRepo(LeaseRepo leaseRepo) {
        this.leaseRepo = leaseRepo;
    }

    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public void generateROU(@PathVariable(name = "name") String lessorName){

//        rouRepo.deleteAll();


        if (leaseRepo.findByNameOfLessor(lessorName) != null){
            leaseFound = leaseRepo.findByNameOfLessor(lessorName);
            if(listOfGeneratedROUChartRepo.findByLessorName(leaseFound.getNameOfLessor()) == null) {


                ListOfGeneratedROUChart lessorToSave = new ListOfGeneratedROUChart();
                lessorToSave.setRowNumber((int) listOfGeneratedROUChartRepo.count()+1);
                lessorToSave.setLessorName(leaseFound.getNameOfLessor());

                listOfGeneratedROUChartRepo.save(lessorToSave);


                endOfMonthForContractExDate = leaseFound.getContractExpiryDate().withDayOfMonth(leaseFound.getContractExpiryDate().getMonth().length(leaseFound.getContractExpiryDate().isLeapYear()));

                endOfMonthForContractExDate1 = endOfMonthForContractExDate;

                while (endOfMonthForContractExDate1.getMonth().getValue() != 6) {
                    endOfMonthForContractExDate1 = endOfMonthForContractExDate1.plusMonths(1);
                }
                endOfMonthForContractExDate1 = endOfMonthForContractExDate1.withDayOfMonth(endOfMonthForContractExDate1.getMonth().length(endOfMonthForContractExDate1.isLeapYear()));

                monthsInBetween = ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(), endOfMonthForContractExDate.plusDays(1));
//            monthsInBetween1 = ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(), endOfMonthForContractExDate.plusDays(1));

                openingROU = leaseFound.getLeaseLiabilityPV() + leaseFound.getInitialPayment();

                endOfMonthForContractCommencementDate = leaseFound.getContractCommencementDate().withDayOfMonth(leaseFound.getContractCommencementDate().getMonth().length(leaseFound.getContractCommencementDate().isLeapYear()));


                for (int i = 0; i < monthsInBetween; i++) {


                    ROU rou1 = new ROU();
                    rou1.setRightOUOpening(openingROU);


                    counter = counter + 1;
                    eachMonth = endOfMonthForContractCommencementDate.plusMonths(i);
                    eachMonth = eachMonth.withDayOfMonth(eachMonth.getMonth().length(eachMonth.isLeapYear()));
                    depreciationROU = openingROU / (monthsInBetween - i);
                    if (eachMonth.getMonth().getValue() == 6 || endOfMonthForContractExDate.isEqual(eachMonth)) {
                        rou1.setTotalDepreciationExpense(depreciationROU * counter);
                        counter = 0;
                    } else {
                        rou1.setTotalDepreciationExpense(0);
                    }

                    closingROU = openingROU - depreciationROU;
                    openingROU = closingROU;

                    rou1.setDepreciationOfROU(depreciationROU);
                    rou1.setRightOUClosing(closingROU);
                    rou1.setLessorName(leaseFound.getNameOfLessor());
                    rou1.setBranchCode(leaseFound.getBranchCode());
                    rou1.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                    rou1.setMonths(eachMonth);
                    rou1.setBranchName(leaseFound.getBranchName());
                    rou1.setBranchDistrict(leaseFound.getBranchDistrict());

                    rouRepo.save(rou1);


                }

//            int i = endOfMonthForContractExDate.getMonth().getValue();
                endOfMonthForContractExDate2 = endOfMonthForContractExDate;

                while (endOfMonthForContractExDate2.getMonth().getValue() != 6) {

                    eachMonth = eachMonth.plusMonths(1);
                    eachMonth = eachMonth.withDayOfMonth(eachMonth.getMonth().length(eachMonth.isLeapYear()));
                    if (eachMonth.getMonth().getValue() < 6) {

                        ROU rou2 = new ROU();

                        rou2.setRightOUOpening(0);
                        rou2.setDepreciationOfROU(0);
                        rou2.setRightOUClosing(0);
                        rou2.setLessorName(leaseFound.getNameOfLessor());
                        rou2.setBranchCode(leaseFound.getBranchCode());
                        rou2.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                        rou2.setMonths(eachMonth);
                        rou2.setTotalDepreciationExpense(0);
                        rou2.setBranchName(leaseFound.getBranchName());
                        rou2.setBranchDistrict(leaseFound.getBranchDistrict());


                        rouRepo.save(rou2);
                        endOfMonthForContractExDate2 = endOfMonthForContractExDate2.plusMonths(1);
                        endOfMonthForContractExDate2 = getEOM(endOfMonthForContractExDate2);
                    } else {
                        ROU rou4 = new ROU();
                        rou4.setRightOUOpening(rouRepo.findByMonthsAndLessorName(endOfMonthForContractExDate, leaseFound.getNameOfLessor()).getRightOUOpening());
                        rou4.setDepreciationOfROU(rouRepo.findByMonthsAndLessorName(endOfMonthForContractExDate, leaseFound.getNameOfLessor()).getDepreciationOfROU());
                        rou4.setRightOUClosing(rouRepo.findByMonthsAndLessorName(endOfMonthForContractExDate, leaseFound.getNameOfLessor()).getRightOUClosing());
                        rou4.setLessorName(leaseFound.getNameOfLessor());
                        rou4.setBranchCode(leaseFound.getBranchCode());
                        rou4.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                        rou4.setMonths(eachMonth);
                        rou4.setTotalDepreciationExpense(rouRepo.findByMonthsAndLessorName(endOfMonthForContractExDate, leaseFound.getNameOfLessor()).getTotalDepreciationExpense());
                        rou4.setBranchName(leaseFound.getBranchName());
                        rou4.setBranchDistrict(leaseFound.getBranchDistrict());

                        rouRepo.save(rou4);
                        endOfMonthForContractExDate2 = endOfMonthForContractExDate2.plusMonths(1);
                        endOfMonthForContractExDate2 = getEOM(endOfMonthForContractExDate2);
                    }
//                ROU rou4 = new ROU();
//                rou4.setRightOUOpening(rouRepo.findByMonths(endOfMonthForContractExDate).getRightOUOpening());
//                rou4.setDepreciationOfROU(rouRepo.findByMonths(endOfMonthForContractExDate).getDepreciationOfROU());
//                rou4.setRightOUClosing(rouRepo.findByMonths(endOfMonthForContractExDate).getRightOUClosing());
//                rou4.setLessorName(leaseFound.getNameOfLessor());
//                rou4.setBranchCode(leaseFound.getBranchCode());
//                rou4.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
//                rou4.setMonths(eachMonth);
//                rou4.setTotalDepreciationExpense(rouRepo.findByMonths(endOfMonthForContractExDate).getTotalDepreciationExpense());
//
//                rouRepo.save(rou4);
//                endOfMonthForContractExDate2 = endOfMonthForContractExDate2.plusMonths(1);
//                endOfMonthForContractExDate2 = getEOM(endOfMonthForContractExDate2);

                }

            }else {
                System.out.println("This ROU chart is already generated!!!");
            }


        /***End of the first if***/
        }else {
            System.out.println("No lease found using this ID!!!");
        }


    }

    public LocalDate getEOM(LocalDate month){

        return month.withDayOfMonth(month.getMonth().length(month.isLeapYear()));

    }


    @RequestMapping(path = "list/{name}", method = RequestMethod.DELETE)
    public void deleteROUList(@PathVariable(name = "name") String lessorNameToDelete){

        if (listOfGeneratedROUChartRepo.findByLessorName(lessorNameToDelete) != null){
            ListOfGeneratedROUChart foundInList = listOfGeneratedROUChartRepo.findByLessorName(lessorNameToDelete);
            listOfGeneratedROUChartRepo.delete(foundInList);

//            ROU foundROU = rouRepo.findByLessorName(lessorNameToDelete);
            rouRepo.deleteAll(rouRepo.findAllByLessorName(lessorNameToDelete));



        }else {
            System.out.println("No lessor found from the given name!!!");
        }


    }

}
