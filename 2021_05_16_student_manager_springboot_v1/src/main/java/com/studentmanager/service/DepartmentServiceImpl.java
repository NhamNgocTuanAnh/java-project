package com.studentmanager.service;

import com.studentmanager.dto.DepartmentDto;
import com.studentmanager.dto.StudentDto;
import com.studentmanager.model.Department;
import com.studentmanager.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void updateStudent(DepartmentDto departmentDto) {
        // TODO Auto-generated method stub
       Department department = departmentRepository.findById(departmentDto.getId()).get();
       department.setId(departmentDto.getId());
       department.setDescription(departmentDto.getDescription());
       department.setName(departmentDto.getName());
    
    }

}
