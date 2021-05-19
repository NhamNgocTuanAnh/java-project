package com.studentmanager.dtos;

import lombok.Data;

import com.studentmanager.entities.Department;

@Data
public class ClassKDto {
    private String id;
    private String name;
    private String COURSE;
    private String description;
    private String department_id;
}
