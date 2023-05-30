package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.model.LeaseLiabilityPV;
import com.example.demo.repository.LeaseLiabilityRepo;
import com.example.demo.repository.LeaseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.lang.Math.pow;

@RestController
@RequestMapping(path = "/api/IFRS/LeaseLiability/")
public class LeaseLiabilityController {



    /************************************************************************************/
    /*for calculating Lease Liability*/

    private double FV;
    private double r = 1.1177;
    private double power;
    private double fraction;
    private long n;
    private long liabilityPeriod;


    /*****************************************************************************************/


    private LeaseRepo leaseRepo;
    private LeaseLiabilityRepo leaseLiabilityRepo;

    @Autowired
    public void setLeaseRepo(LeaseRepo leaseRepo) {
        this.leaseRepo = leaseRepo;
    }

    @Autowired
    public void setLeaseLiabilityRepo(LeaseLiabilityRepo leaseLiabilityRepo) {
        this.leaseLiabilityRepo = leaseLiabilityRepo;
    }


    @RequestMapping(path = "{name}",method = RequestMethod.POST)
    public LeaseLiabilityPV saveLiabilityPayment(LeaseLiabilityPV leaseLiabilityPV,@PathVariable(name = "name") String lessorName){
        Lease lease1 = leaseRepo.findByNameOfLessor(lessorName);

        leaseLiabilityPV.setAmountLeaseLiability(totalInstallmentLL(lessorName));
        leaseLiabilityPV.setLessorName(lease1.getNameOfLessor());
        leaseLiabilityPV.setBranchCode(lease1.getBranchCode());


        return leaseLiabilityRepo.save(leaseLiabilityPV);
    }

    @RequestMapping(path = "allLeasePayments", method = RequestMethod.GET)
    public List<LeaseLiabilityPV> getLiabilityPayment(){
        return leaseLiabilityRepo.findAll();
    }

    /*********************************taking the name of lessor form the Request Body*************************************************/


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LeaseLiabilityPV saveLiabilityPayment1(LeaseLiabilityPV leaseLiabilityPV,@RequestBody Lease lease){

        Lease lease1 = leaseRepo.findByNameOfLessor(lease.getNameOfLessor());

        leaseLiabilityPV.setAmountLeaseLiability(totalInstallmentLL(lease.getNameOfLessor()));
        leaseLiabilityPV.setLessorName(lease1.getNameOfLessor());
        leaseLiabilityPV.setBranchCode(lease1.getBranchCode());
        leaseLiabilityPV.setCalculatedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
        deleteRemaining();


        return leaseLiabilityRepo.save(leaseLiabilityPV);
    }


    /*************************************************Getting the Last recent Lease Liability*************************************************/



    @RequestMapping(path = "recentLL", method = RequestMethod.GET)
    public List<LeaseLiabilityPV> getRecentLiabilityPayment(){

        return leaseLiabilityRepo.findByOrderByCalculatedAtDesc();
    }


    @RequestMapping(path = "recentLastLL", method = RequestMethod.GET)
    public LeaseLiabilityPV getLastRecentLiabilityPayment(){
        System.out.println("before calling the method");

        System.out.println("After calling the method");
        return leaseLiabilityRepo.findByOrderByCalculatedAtDesc().get(0);
    }



    /*************************************************Deleting older Lease Liability calculations*************************************************/


    public void deleteRemaining(){

        System.out.println(leaseLiabilityRepo.count());

        if(leaseLiabilityRepo.count() > 4){
            System.out.println(leaseLiabilityRepo.count());
            System.out.println("Deleting started");
            for (long i = leaseLiabilityRepo.count(); i > 4; i--) {
                System.out.println("Entering If");
                String idToDelete = leaseLiabilityRepo.findByOrderByCalculatedAtDesc().get(4).getId();
                leaseLiabilityRepo.deleteById(idToDelete);
                System.out.println("Leaving If");
                System.out.println("Deleting Ends");
            }
            System.out.println(leaseLiabilityRepo.count());

        }
    }

    /*************************************************Deleting all Lease Liability calculations*************************************************/

    @RequestMapping(path = "deleteAllLL", method = RequestMethod.DELETE)
    public void deleteAllLL(){
        leaseLiabilityRepo.deleteAll();

    }



    /*************************************************External Methods*************************************************/
    public double InstallmentLL(String lessorName, long whichInstallment){


        Lease foundLease = leaseRepo.findByNameOfLessor(lessorName);
        FV = foundLease.getAnnualRentalFee();


        n = (int) (foundLease.getRemainingMonthsForPrepaidRentAfterInitialApplication() / 12) + (whichInstallment);


        power = pow(r, n);


        fraction = 1/power;



        return FV * fraction;

    }

    public double totalInstallmentLL(String lessorName1){

        Lease foundLease = leaseRepo.findByNameOfLessor(lessorName1);


        liabilityPeriod = foundLease.getLeaseLiabilityPeriod();


        double liabilitySummation = 0;
        for (int i = 0; i < liabilityPeriod; i++){

            liabilitySummation = liabilitySummation + InstallmentLL(lessorName1, i);

        }
        return liabilitySummation;
    }

}
