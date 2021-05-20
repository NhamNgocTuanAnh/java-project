package com.studentmanager.dto;

import com.studentmanager.model.Department;

import lombok.Data;

@Data
public class ClassKDto {
    private String id;
    private String name;
    private String COURSE;
    private String description;
    private String department_id;
}
