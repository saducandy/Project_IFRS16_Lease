package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.repository.LeaseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/IFRS/")
public class LeaseController {

    private LeaseRepo leaseRepo;
    private Logger LOG = LoggerFactory.getLogger(LeaseController.class);
    @Autowired
    public void setLeaseRepo(LeaseRepo leaseRepo) {
        this.leaseRepo = leaseRepo;
    }

    @RequestMapping(path = "allLeases", method = RequestMethod.GET)
    public List<Lease> getLeaseDetail(){
        return leaseRepo.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public List<Lease> getLeaseDetail(@PathVariable(name = "id") long id){
        if (leaseRepo.findById(id).isPresent()) {
            return (List<Lease>) leaseRepo.findById(id).get();
        }else {
            LOG.info("No Lease found with the given ID");
            return leaseRepo.findAll();
        }
    }



    @RequestMapping(path = "bc/{abc}", method = RequestMethod.GET)
    public Lease getLeaseByBC(@PathVariable(name = "abc")String branchcode1){
       return leaseRepo.findByBranchCode(branchcode1);
    }


    @RequestMapping(path = "name/{leaserName}", method = RequestMethod.GET)
    public List<Lease> getLeaseByName(@PathVariable(name = "leaserName")String name){
        return leaseRepo.findByNameOfLessor(name);
    }




    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Lease createLease(@RequestBody Lease infoLease){

        return leaseRepo.save(infoLease);

    }


    @RequestMapping (path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Lease updateLeaseDetail(@PathVariable(name = "id") long leaseID, @RequestBody Lease leaseToUpdate) {



            if (leaseRepo.findById(leaseID).isPresent()) {
                Lease leaseFound = leaseRepo.findById(leaseID).get();

                leaseFound.setBranchCode(leaseToUpdate.getBranchCode());
                leaseFound.setLeaseCategoryType(leaseToUpdate.getLeaseCategoryType());
                leaseFound.setContractAgreementPeriod(leaseToUpdate.getContractAgreementPeriod());
                leaseFound.setShortTermLease(leaseToUpdate.isShortTermLease());
                leaseFound.setInitialPayment(leaseToUpdate.getInitialPayment());
                leaseFound.setContractAgreementPeriod(leaseToUpdate.getContractAgreementPeriod());
                leaseFound.setContractCommencementDate(leaseToUpdate.getContractCommencementDate());
                leaseFound.setContractExpiryDate(leaseToUpdate.getContractExpiryDate());
                leaseFound.setFirstInstallmentDate(leaseToUpdate.getFirstInstallmentDate());
                leaseFound.setInterestRate(leaseToUpdate.getInterestRate());
                leaseFound.setLowValueAsset(leaseToUpdate.isLowValueAsset());
                leaseFound.setMonthlyAmortizationAmountOfPrepaidLease(leaseToUpdate.getMonthlyAmortizationAmountOfPrepaidLease());
                leaseFound.setMonthlyAmortizationAmountOfUnpaidLease(leaseToUpdate.getMonthlyAmortizationAmountOfUnpaidLease());
                leaseFound.setNameOfLessor(leaseToUpdate.getNameOfLessor());
                leaseFound.setPrePaymentEnd_Date(leaseToUpdate.getPrePaymentEnd_Date());
                leaseFound.setRemainingMonthsForPrepaidRentAfterInitialApplication(leaseToUpdate.getRemainingMonthsForPrepaidRentAfterInitialApplication());
                leaseFound.setRemainingMonthsInContractTermNotPaidAfterInitialApplication(leaseToUpdate.getRemainingMonthsInContractTermNotPaidAfterInitialApplication());
                leaseFound.setReportedBy(leaseToUpdate.getReportedBy());

                return leaseRepo.save(leaseFound);
            }else {
                LOG.info("No Leases found with the given ID!!");
                return leaseToUpdate;
            }


    }

}
