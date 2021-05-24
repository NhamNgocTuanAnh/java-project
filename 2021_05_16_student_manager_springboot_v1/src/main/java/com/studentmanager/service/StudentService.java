package com.studentmanager.service;

import java.util.List;

import com.studentmanager.dto.StudentAnlDto;
import com.studentmanager.dto.StudentDto;
import com.studentmanager.model.Student;

public interface StudentService {
    
    List<Student> pageableStudent(int pagenumber,int rowsofpage);
    List<StudentAnlDto> analyList(int startAge,int endAge); 
    Student saveStudent(StudentDto studentDto);
    Student updateStudent(Student student,StudentDto studentDto);
}