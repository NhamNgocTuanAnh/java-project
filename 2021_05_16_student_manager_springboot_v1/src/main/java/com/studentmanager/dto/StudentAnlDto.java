package com.studentmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StudentAnlDto {
    private Long count;
    private String department_name;
    private String class_name;

    public StudentAnlDto() {
    }

    public StudentAnlDto(Long count, String department_name, String class_name) {
this.count = count;
this.department_name = department_name;
this.class_name = class_name;
    }
}
