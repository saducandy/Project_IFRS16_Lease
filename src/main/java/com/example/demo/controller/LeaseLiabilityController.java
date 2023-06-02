package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.model.LeaseLiabilityPV;
import com.example.demo.repository.LeaseLiabilityRepo;
import com.example.demo.repository.LeaseRepo;
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
    private double amountLeaseLiability;


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
        if (leaseRepo.findByNameOfLessor(lessorName) != null) {
            Lease lease1 = leaseRepo.findByNameOfLessor(lessorName);

            amountLeaseLiability = totalInstallmentLL(lessorName);
            leaseLiabilityPV.setAmountLeaseLiability(amountLeaseLiability);
            leaseLiabilityPV.setLessorName(lease1.getNameOfLessor());
            leaseLiabilityPV.setBranchCode(lease1.getBranchCode());
            leaseLiabilityPV.setCalculatedAt(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            deleteRemaining();

            updateLeaseEntity(lessorName);
            return leaseLiabilityRepo.save(leaseLiabilityPV);
        }else {
            System.out.println("No lease found with the given name!!!");
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.PUT)
    public void updateLeaseEntity(String lessorName2){
        Lease lease2 = leaseRepo.findByNameOfLessor(lessorName2);


        lease2.setLeaseLiabilityPV(amountLeaseLiability);

        leaseRepo.save(lease2);


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

        return leaseLiabilityRepo.findByOrderByCalculatedAtDesc().get(0);
    }



    /*************************************************Deleting older Lease Liability calculations*************************************************/


    public void deleteRemaining(){


        if(leaseLiabilityRepo.count() > 4){
            for (long i = leaseLiabilityRepo.count(); i > 4; i--) {
                String idToDelete = leaseLiabilityRepo.findByOrderByCalculatedAtDesc().get(4).getId();
                leaseLiabilityRepo.deleteById(idToDelete);
            }

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
