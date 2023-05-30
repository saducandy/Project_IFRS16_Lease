package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.model.LeaseLiabilityPV;
import com.example.demo.repository.LeaseLiabilityRepo;
import com.example.demo.repository.LeaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


        return leaseLiabilityRepo.save(leaseLiabilityPV);
    }



    /*************************************************External Methods*************************************************/
    public double InstallmentLL(String lessorName, long whichInstallment){


        Lease foundLease = leaseRepo.findByNameOfLessor(lessorName);
        FV = foundLease.getAnnualRentalFee();
        System.out.println("New Round");
        System.out.println(FV);

        n = (int) (foundLease.getRemainingMonthsForPrepaidRentAfterInitialApplication() / 12) + (whichInstallment);
        System.out.println(n);

        power = pow(r, n);
        System.out.println(power);

        fraction = 1/power;
        System.out.println(fraction);

        System.out.println((FV * fraction));
        return FV * fraction;

    }

    public double totalInstallmentLL(String lessorName1){

        Lease foundLease = leaseRepo.findByNameOfLessor(lessorName1);
        System.out.println("liability-Period");

        liabilityPeriod = foundLease.getLeaseLiabilityPeriod();
        System.out.println(liabilityPeriod);

        double liabilitySummation = 0;
        for (int i = 0; i < liabilityPeriod; i++){

            liabilitySummation = liabilitySummation + InstallmentLL(lessorName1, i);

        }
        return liabilitySummation;
    }

}
