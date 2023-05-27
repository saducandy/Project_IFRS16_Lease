package com.example.demo.controller;

import com.example.demo.model.LessorDetail;
import com.example.demo.repository.LessorDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/IFRS/LessorDetail/")
public class LessorDetailController {


    private Logger LOG = LoggerFactory.getLogger(LessorDetailController.class);
    private LessorDetailRepo lessorDetailRepo;

    @Autowired
    public void setLessorDetailRepo(LessorDetailRepo lessorDetailRepo) {
        this.lessorDetailRepo = lessorDetailRepo;
    }

    @RequestMapping(path = "allLessors", method = RequestMethod.GET)
    public List<LessorDetail> getAllLessors(){
        return lessorDetailRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LessorDetail getLessorDetail(@RequestBody LessorDetail lessor){

        return lessorDetailRepo.save(lessor);

    }



    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public LessorDetail getLessorDetail(@PathVariable(name = "id") String lessorDetailID){

        if (lessorDetailRepo.findById(lessorDetailID).isPresent()){
            return lessorDetailRepo.findById(lessorDetailID).get();
        }else {
            long i =lessorDetailRepo.count();
            for (long j=0; j<i; j++) {
                return lessorDetailRepo.findAll().get((int) j);
            }
        }
        return null;

    }
    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LessorDetail putLessorDetail(@RequestBody LessorDetail detailToUpdate, @PathVariable(name = "id") String idToUpdate) {



        if (lessorDetailRepo.findById(idToUpdate).isPresent()){
            LessorDetail lessorDetailFound = lessorDetailRepo.findById(idToUpdate).get();


            lessorDetailFound.setCity(detailToUpdate.getCity());
            lessorDetailFound.setName(detailToUpdate.getName());
            lessorDetailFound.setWereda(detailToUpdate.getWereda());
            lessorDetailFound.setHouseNumber(detailToUpdate.getHouseNumber());
            lessorDetailFound.setPhoneNumber(detailToUpdate.getPhoneNumber());
            lessorDetailFound.setMapNumber(detailToUpdate.getMapNumber());

            return lessorDetailRepo.save(lessorDetailFound);


        }else {
            LOG.info("No Lessor Found for this ID");
            return detailToUpdate;
        }

    }


    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteLessor(@PathVariable(name = "id") String id){

        if (lessorDetailRepo.findById(id).isPresent()) {

            long i = lessorDetailRepo.count();
            LOG.info("Lessors before deletion:{i}");
            lessorDetailRepo.deleteById(id);
            long j = lessorDetailRepo.count();
            LOG.info("Lessors before deletion:");
        }else {
            LOG.info("No lessor found with the given ID");
        }


    }




}
