package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class District {

    @Id
    private Long districtId;
    private String districtName;

    /**Getters and Setters**/
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


    /***************************/


    /**To String**/
    @Override
    public String toString() {
        return "Distrinct{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                '}';
    }


    /****************/


}
