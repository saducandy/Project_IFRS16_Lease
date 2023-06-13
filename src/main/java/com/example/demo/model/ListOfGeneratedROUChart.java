package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ListOfGeneratedROUChart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
//    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowNumber;
    @Column(unique = true)
    private String lessorName;


    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return "ListOfGeneratedROUChart{" +
                "id='" + id + '\'' +
                ", lessorName='" + lessorName + '\'' +
                '}';
    }
}
