package com.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StudentAnlDto {
    private Long count;
    private String class_name;

    private String department_name;

    public StudentAnlDto() {
    }

    public StudentAnlDto(Long count, String class_name, String department_name) {
        this.count = count;
        this.class_name = class_name;
        this.department_name = department_name;

    }
}
