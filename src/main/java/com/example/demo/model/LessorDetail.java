package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class LessorDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String lessorID;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String wereda;
    @NotNull
    private String houseNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String mapNumber;


    public String getLessorID() {
        return lessorID;
    }

    public void setLessorID(String lessorID) {
        this.lessorID = lessorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(String mapNumber) {
        this.mapNumber = mapNumber;
    }


    @Override
    public String toString() {
        return "LessorDetail{" +
                "lessorID='" + lessorID + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", wereda='" + wereda + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mapNumber='" + mapNumber + '\'' +
                '}';
    }
}
