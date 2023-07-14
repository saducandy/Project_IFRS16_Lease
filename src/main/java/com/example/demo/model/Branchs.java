package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branchs {

    @Id
    private Long branchCode;
    private String branchName;
    private Long district;


    /**Getters and Setters**/
    public Long getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Long branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }


    /************************/


    /**To String**/
    @Override
    public String toString() {
        return "Branchs{" +
                "branchCode=" + branchCode +
                ", branchName='" + branchName + '\'' +
                ", district=" + district +
                '}';
    }


    /****************/

}
