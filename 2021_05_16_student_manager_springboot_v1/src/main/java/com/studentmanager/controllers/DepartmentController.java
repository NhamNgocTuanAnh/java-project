package com.studentmanager.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.studentmanager.dto.DepartmentDto;
import com.studentmanager.model.Department;
import com.studentmanager.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody DepartmentDto departmentDto) {

        Department department = new Department();
        department.setDescription(departmentDto.getDescription());
        department.setName(departmentDto.getName());
        department.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
        department.setTIME_CREATE(Timestamp.valueOf(LocalDateTime.now()));
        departmentRepository.saveAndFlush(department);

        return department;
    }

    @GetMapping("/department")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PutMapping("/department")
    public Department update(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId()).get();
        String name = departmentDto.getName();
        String description = departmentDto.getDescription();
        if (department != null) {
            if (name != null) {
                department.setName(name);
            }
            if(description != null){
                department.setDescription(description);
            }
            department.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
            departmentRepository.save(department);
        }

        return department;
    }

    @DeleteMapping("/department/{id}")
    public String delete(@PathVariable String id) {
        departmentRepository.deleteById(id);
        return "department has been deleted with id:" + id;
    }
    @GetMapping("/department/{id}")
    public Department getDe(@PathVariable String id) {
        return departmentRepository.findById(id).get();
    
    }
}
