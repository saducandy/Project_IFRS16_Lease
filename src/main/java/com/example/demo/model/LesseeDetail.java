package com.example.demo.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class LesseeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String lesseeID;
    @Column(nullable = false)
    private String branchName;

    private String city;

    private String wereda;

    private String houseNumber;

    private String phoneNumber;

    @Column(nullable = false)
    private String branchCode;
    @Column(nullable = false)
    private String district;

    private String postalBoxNumber;


    public String getLesseeID() {
        return lesseeID;
    }

    public void setLesseeID(String lesseeID) {
        this.lesseeID = lesseeID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWereda() {
        return wereda;
    }

    public void setWereda(String wereda) {
        this.wereda = wereda;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalBoxNumber() {
        return postalBoxNumber;
    }

    public void setPostalBoxNumber(String postalBoxNumber) {
        this.postalBoxNumber = postalBoxNumber;
    }


    @Override
    public String toString() {
        return "LesseeDetail{" +
                "lesseeID='" + lesseeID + '\'' +
                ", branchName='" + branchName + '\'' +
                ", city='" + city + '\'' +
                ", wereda='" + wereda + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", district='" + district + '\'' +
                ", postalBoxNumber='" + postalBoxNumber + '\'' +
                '}';
    }
}
