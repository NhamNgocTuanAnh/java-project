package com.studentmanager.service;

import java.util.List;

import com.studentmanager.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

public interface StudentService {
    
  
    // List<Student> findByName1(String name,int position,int pagesize) ;

    List<Student> pageableStudent(int pagenumber,int rowsofpage);
}