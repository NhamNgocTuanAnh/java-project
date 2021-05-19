package com.studentmanager.controllers;

import java.util.List;

import com.studentmanager.entities.Department;
import com.studentmanager.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department departmentObj) {
        Department nDepartment = new Department();
    
        nDepartment.setDescription(departmentObj.getDescription());
        // nDepartment.setName(departmentObj.getName());
        nDepartment.setId(3);
        departmentRepository.saveAndFlush(nDepartment);
        return nDepartment;
    }
    @GetMapping("/department")
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}
    @PutMapping("/department")
    public Department update(@RequestBody Department employeeObj) {
       
        departmentRepository.save(employeeObj);
        return employeeObj;
    }
    @DeleteMapping("/department/{id}")
    public String delete(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
        return "department has been deleted with id:" + id;
    }
}
