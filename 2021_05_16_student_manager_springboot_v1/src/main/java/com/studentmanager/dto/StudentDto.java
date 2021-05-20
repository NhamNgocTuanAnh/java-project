package com.studentmanager.dto;

import java.util.Date;

import com.studentmanager.model.ClassK;

import lombok.Data;

@Data
public class StudentDto {

    private String id;
    private String name;
    private Date BIRTHDATE;
    private int gender;
    private String address;
    private String classk_id;

}
