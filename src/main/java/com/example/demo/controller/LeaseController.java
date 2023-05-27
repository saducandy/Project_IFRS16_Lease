package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.repository.LeaseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(path = "/api/IFRS/")
public class LeaseController {

    private double monthlyPaymentOfPrePaidOrUnpaid;

    private double monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO;
    private LeaseRepo leaseRepo;
    private long defaultId = 1234;
    private int monthsInYear = 12;

    private LocalDate endOfMonth;
    private LocalDate endOfMonthForPrepaymentEndDate;
    private LocalDate nextDateForPrepaymentEndDate;
    private LocalDate nextDate;
    private double TCP;
    private double totalPeriodPayment1;
    private double CVP;

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
    public Lease getLeaseDetail(@PathVariable(name = "id") long id){

        if (leaseRepo.findById(id).isPresent()) {
            return leaseRepo.findById(id).get();
        }else {
            LOG.info("No Lease found with the given ID");
            return leaseRepo.findById(defaultId).get();
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

        //calculating the monthly payment of the Lease with vat/TTO and without vat/TTO
        monthlyPaymentOfPrePaidOrUnpaid = infoLease.getArea() * infoLease.getPaymentPerCare();
        monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO = monthlyPaymentOfPrePaidOrUnpaid + (monthlyPaymentOfPrePaidOrUnpaid * infoLease.getVatOrTTO());
        infoLease.setMonthlyAmortizationAmountOfPrepaidLease(monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO);
        infoLease.setMonthlyAmortizationAmountOfUnpaidLease(monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO);


        //calculating annual Rental Fee
        infoLease.setAnnualRentalFee(monthsInYear * infoLease.getMonthlyAmortizationAmountOfPrepaidLease());

//        Period difference = Period.between(infoLease.getContractCommencementDate(), infoLease.getContractExpiryDate())



        //calculating contract Agreement period
//        infoLease.setContractAgreementPeriod();

        endOfMonth= infoLease.getContractExpiryDate().withDayOfMonth(infoLease.getContractExpiryDate().getMonth().length(infoLease.getContractExpiryDate().isLeapYear()));
        nextDate = endOfMonth.plusDays(1);
        infoLease.setContractAgreementPeriod(ChronoUnit.MONTHS.between(infoLease.getContractCommencementDate(),nextDate));


        //calculating prepayment end date and pre payment period
        endOfMonthForPrepaymentEndDate = infoLease.getPrePaymentEndDate().withDayOfMonth(infoLease.getPrePaymentEndDate().getMonth().length(infoLease.getPrePaymentEndDate().isLeapYear()));
        nextDateForPrepaymentEndDate = endOfMonthForPrepaymentEndDate.plusDays(1);
        infoLease.setRemainingMonthsForPrepaidRentAfterInitialApplication(ChronoUnit.MONTHS.between(infoLease.getContractCommencementDate(),nextDateForPrepaymentEndDate));


        //calculating RemainingMonthsInContractTermNotPaidAfterInitialApplication
        infoLease.setRemainingMonthsInContractTermNotPaidAfterInitialApplication(ChronoUnit.MONTHS.between(nextDateForPrepaymentEndDate, nextDate));

        //calculating totalPeriodPayment
        totalPeriodPayment1 = infoLease.getContractAgreementPeriod() * infoLease.getMonthlyAmortizationAmountOfPrepaidLease();
        infoLease.setTotalPeriodPayment(totalPeriodPayment1);
        CVP = totalPeriodPayment1 * infoLease.getContractVatPercentage();
        infoLease.setContractVatPayment(CVP);
        TCP = totalPeriodPayment1 + CVP;
        infoLease.setTotalContractPrice(TCP);


        //initial payment calculation
        infoLease.setInitialPayment(infoLease.getMonthlyAmortizationAmountOfPrepaidLease() * infoLease.getRemainingMonthsForPrepaidRentAfterInitialApplication());



        return leaseRepo.save(infoLease);

    }


    @RequestMapping (path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Lease updateLeaseDetail(@PathVariable(name = "id") long leaseID, @RequestBody Lease leaseToUpdate) {



            if (leaseRepo.findById(leaseID).isPresent()) {
                Lease leaseFound = leaseRepo.findById(leaseID).get();

                leaseFound.setArea(leaseToUpdate.getArea());
                leaseFound.setPaymentPerCare(leaseToUpdate.getPaymentPerCare());
                leaseFound.setVatOrTTO(leaseToUpdate.getVatOrTTO());
                leaseFound.setContractVatPercentage(leaseToUpdate.getContractVatPercentage());
                leaseFound.setBranchCode(leaseToUpdate.getBranchCode());
                leaseFound.setLeaseCategoryType(leaseToUpdate.getLeaseCategoryType());
                leaseFound.setContractAgreementPeriod(leaseToUpdate.getContractAgreementPeriod());
                leaseFound.setShortTermLease(leaseToUpdate.isShortTermLease());

                leaseFound.setContractAgreementPeriod(leaseToUpdate.getContractAgreementPeriod());
                leaseFound.setContractCommencementDate(leaseToUpdate.getContractCommencementDate());
                leaseFound.setContractExpiryDate(leaseToUpdate.getContractExpiryDate());
                leaseFound.setFirstInstallmentDate(leaseToUpdate.getFirstInstallmentDate());
                leaseFound.setInterestRate(leaseToUpdate.getInterestRate());
                leaseFound.setLowValueAsset(leaseToUpdate.isLowValueAsset());
                leaseFound.setPrePaymentEndDate(leaseToUpdate.getPrePaymentEndDate());



                //calculating the monthly payment of the Lease with vat/TTO and without vat/TTO
                monthlyPaymentOfPrePaidOrUnpaid = leaseFound.getArea() * leaseFound.getPaymentPerCare();
                monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO = monthlyPaymentOfPrePaidOrUnpaid + (monthlyPaymentOfPrePaidOrUnpaid * leaseFound.getVatOrTTO());
                leaseFound.setMonthlyAmortizationAmountOfPrepaidLease(monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO);
                leaseFound.setMonthlyAmortizationAmountOfUnpaidLease(monthlyPaymentOfPrePaidOrUnpaidWithVatOrTTO);

                //calculating annual Rental Fee
                leaseFound.setAnnualRentalFee(monthsInYear * leaseFound.getMonthlyAmortizationAmountOfPrepaidLease());

                //calculating Contract agreement period for put request
                endOfMonth= leaseFound.getContractExpiryDate().withDayOfMonth(leaseFound.getContractExpiryDate().getMonth().length(leaseFound.getContractExpiryDate().isLeapYear()));


                nextDate = endOfMonth.plusDays(1);
                leaseFound.setContractAgreementPeriod(ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(),nextDate));

                //calculating prepayment end date and pre payment period
                endOfMonthForPrepaymentEndDate = leaseFound.getPrePaymentEndDate().withDayOfMonth(leaseFound.getPrePaymentEndDate().getMonth().length(leaseFound.getPrePaymentEndDate().isLeapYear()));
                nextDateForPrepaymentEndDate = endOfMonthForPrepaymentEndDate.plusDays(1);
                leaseFound.setRemainingMonthsForPrepaidRentAfterInitialApplication(ChronoUnit.MONTHS.between(leaseFound.getContractCommencementDate(),nextDateForPrepaymentEndDate));

                leaseFound.setNameOfLessor(leaseToUpdate.getNameOfLessor());

                //calculating RemainingMonthsInContractTermNotPaidAfterInitialApplication
                leaseFound.setRemainingMonthsInContractTermNotPaidAfterInitialApplication(ChronoUnit.MONTHS.between(nextDateForPrepaymentEndDate, nextDate));

                //calculating TCP for put
                totalPeriodPayment1 = leaseFound.getContractAgreementPeriod() * leaseFound.getMonthlyAmortizationAmountOfPrepaidLease();
                leaseFound.setTotalPeriodPayment(totalPeriodPayment1);
                CVP = totalPeriodPayment1 * leaseFound.getContractVatPercentage();
                leaseFound.setContractVatPayment(CVP);
                TCP = totalPeriodPayment1 + CVP;
                leaseFound.setTotalContractPrice(TCP);

                //initial payment calculation
                leaseFound.setInitialPayment(leaseFound.getMonthlyAmortizationAmountOfPrepaidLease() * leaseFound.getRemainingMonthsForPrepaidRentAfterInitialApplication());



                leaseFound.setReportedBy(leaseToUpdate.getReportedBy());

                return leaseRepo.save(leaseFound);

            }else {
                LOG.info("No Leases found with the given ID!!");
                return leaseToUpdate;
            }


    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteLease(@PathVariable(name = "id") long idOfLease){

        if(leaseRepo.findById(idOfLease).isPresent()) {
            leaseRepo.deleteById(idOfLease);
            LOG.info("Successfully deleted a Lease");

        }else {
            LOG.info("There is no Lease found with the ID given");

        }
    }

}
