package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ListOfGeneratedLLChart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String lessorName;


    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    @Override
    public String toString() {
        return "ListOfGeneratedLLChart{" +
                "id='" + id + '\'' +
                ", lessorName='" + lessorName + '\'' +
                '}';
    }
}
