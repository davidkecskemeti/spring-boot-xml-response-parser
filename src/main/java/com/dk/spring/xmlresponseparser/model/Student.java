package com.dk.spring.xmlresponseparser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String avatar;

}
