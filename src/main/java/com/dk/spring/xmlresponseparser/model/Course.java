package com.dk.spring.xmlresponseparser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Course {

    private int id;
    private String title;
    private double price;
    private Date created;
    private List<Student> students;

    public Course(int id, String title, double price, Date created) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.created = created;
    }
}
