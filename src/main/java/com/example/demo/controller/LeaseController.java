package com.example.demo.controller;

import com.example.demo.model.Lease;
import com.example.demo.repository.LeaseRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/IFRS/")
public class LeaseController {

    private LeaseRepo leaseRepo;
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
        return leaseRepo.findById(id).get();
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




}
