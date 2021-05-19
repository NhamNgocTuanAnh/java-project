package com.studentmanager.dtos;

import java.util.Date;

import com.studentmanager.entities.ClassK;

import lombok.Data;

@Data
public class StudentDto {

    private String id;
    private String name;
    private Date BIRTHDATE;
    private int gender;
    private String address;
    private ClassK classK;

}
