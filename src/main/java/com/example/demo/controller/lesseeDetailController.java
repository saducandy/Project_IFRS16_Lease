package com.example.demo.controller;

import com.example.demo.model.LesseeDetail;
import com.example.demo.model.LessorDetail;
import com.example.demo.repository.LesseeDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/IFRS/LesseeDetail/")
public class lesseeDetailController {


    private Logger LOG = LoggerFactory.getLogger(LessorDetailController.class);
    private LesseeDetailRepo lesseeDetailRepo;

    @Autowired
    public void setLesseeDetailRepo(LesseeDetailRepo lesseeDetailRepo) {
        this.lesseeDetailRepo = lesseeDetailRepo;
    }


    @RequestMapping(path = "allLessees", method = RequestMethod.GET)
    public List<LesseeDetail> getAllLessors(){
        return lesseeDetailRepo.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LesseeDetail getLesseeDetail(@RequestBody LesseeDetail lessee){

        return lesseeDetailRepo.save(lessee);

    }



    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public LesseeDetail getLesseeDetail(@PathVariable(name = "id") String lesseeDetailID){

        if (lesseeDetailRepo.findById(lesseeDetailID).isPresent()){
            return lesseeDetailRepo.findById(lesseeDetailID).get();
        }else {
            LOG.info("No lessor found with the given ID.");
            LOG.info("Returning the default lessor");
            return lesseeDetailRepo.findAll().get(1);


        }


    }
    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LesseeDetail putLesseeDetail(@RequestBody LesseeDetail detailToUpdate, @PathVariable(name = "id") String idToUpdate) {



        if (lesseeDetailRepo.findById(idToUpdate).isPresent()){
            LesseeDetail lesseeDetailFound = lesseeDetailRepo.findById(idToUpdate).get();


            lesseeDetailFound.setCity(detailToUpdate.getCity());
            lesseeDetailFound.setBranchName(detailToUpdate.getBranchName());
            lesseeDetailFound.setWereda(detailToUpdate.getWereda());
            lesseeDetailFound.setHouseNumber(detailToUpdate.getHouseNumber());
            lesseeDetailFound.setPhoneNumber(detailToUpdate.getPhoneNumber());
            lesseeDetailFound.setBranchCode(detailToUpdate.getBranchCode());
            lesseeDetailFound.setDistrict(detailToUpdate.getDistrict());
            lesseeDetailFound.setPostalBoxNumber(detailToUpdate.getPostalBoxNumber());




            return lesseeDetailRepo.save(lesseeDetailFound);


        }else {
            LOG.info("No Lessee Found for this ID");
            return detailToUpdate;
        }

    }


    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteLessee(@PathVariable(name = "id") String id){

        if (lesseeDetailRepo.findById(id).isPresent()) {

            long i = lesseeDetailRepo.count();
            LOG.info("Lessors before deletion:{i}");
            lesseeDetailRepo.deleteById(id);
            long j = lesseeDetailRepo.count();
            LOG.info("Lessors before deletion:");
        }else {
            LOG.info("No lessor found with the given ID");
        }


    }




}