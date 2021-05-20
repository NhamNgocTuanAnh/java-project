package com.studentmanager.service;
import java.util.List;

import com.studentmanager.model.Student;
import com.studentmanager.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

 

    @Override
    public List<Student> findByName1(String name,int position,int pagesize) {
        Sort sort = Sort.by("name").descending();
        Pageable pageable = PageRequest.of(position, pagesize, sort);
        return studentRepository.findByName1(name, pageable);
    }
    @Override
    public List<Student> pageableStudent(int pagenumber,int rowsofpage){
        return studentRepository.pageableStudent(pagenumber,rowsofpage);
    }
}
