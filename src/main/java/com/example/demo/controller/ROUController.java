package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.model.ROU;
import com.example.demo.repository.LeaseRepo;
import com.example.demo.repository.ROURepo;
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
    private LocalDate endOfMonthForContractExDate;
    private double openingROU;
    private double closingROU;
    private double depreciationROU;
    private String lessorName1;
    private String branchCode;
    private LocalDate eachMonth;
    private LocalDate endOfMonthForContractCommencementDate;


    /*Autowiring the Repository classes*/

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

        rouRepo.deleteAll();

        if (leaseRepo.findByNameOfLessor(lessorName) != null){
            leaseFound = leaseRepo.findByNameOfLessor(lessorName);

            endOfMonthForContractExDate = leaseFound.getContractExpiryDate().withDayOfMonth(leaseFound.getContractExpiryDate().getMonth().length(leaseFound.getContractExpiryDate().isLeapYear()));

            monthsInBetween = ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(), endOfMonthForContractExDate.plusDays(1));

            openingROU = leaseFound.getLeaseLiabilityPV() + leaseFound.getInitialPayment();

            endOfMonthForContractCommencementDate = leaseFound.getContractCommencementDate().withDayOfMonth(leaseFound.getContractCommencementDate().getMonth().length(leaseFound.getContractCommencementDate().isLeapYear()));



            for (int i = 0; i < monthsInBetween; i++){


                ROU rou1 = new ROU();
                rou1.setRightOUOpening(openingROU);
                System.out.println(openingROU);


                eachMonth = endOfMonthForContractCommencementDate.plusMonths(i);
                eachMonth = eachMonth.withDayOfMonth(eachMonth.getMonth().length(eachMonth.isLeapYear()));
                depreciationROU = openingROU / (monthsInBetween - i);
//                if (i != monthsInBetween) {
//                    depreciationROU = openingROU / (monthsInBetween - i);
//                }
                closingROU = openingROU - depreciationROU;
                openingROU = closingROU;

                rou1.setDepreciationOfROU(depreciationROU);
                rou1.setRightOUClosing(closingROU);
                System.out.println(closingROU);
                rou1.setLessorName(leaseFound.getNameOfLessor());
                rou1.setBranchCode(leaseFound.getBranchCode());
                rou1.setGeneratedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
                rou1.setMonths(eachMonth);

                rouRepo.save(rou1);


            }

        }else {
            System.out.println("No lease found using this ID!!!");
        }


    }

}
