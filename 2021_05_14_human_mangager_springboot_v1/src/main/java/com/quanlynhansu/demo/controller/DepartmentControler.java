package com.quanlynhansu.demo.controller;

import java.util.List;

import com.quanlynhansu.demo.entity.Department;
import com.quanlynhansu.demo.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DepartmentControler {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<Department>> findAllDepartment() {
        List<Department> department = departmentService.get();
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @GetMapping("/cout-department")
    public ResponseEntity<Long> countDepartment() {
        return new ResponseEntity<>(departmentService.countTotalRecords(), HttpStatus.OK);
    }
    @GetMapping("/all-department")
    public ResponseEntity<List<Department>> findAllDepartmentWithPagintation(
            @RequestParam(defaultValue = "1") int position, @RequestParam(defaultValue = "3") int pageSize) {
        List<Department> departments = departmentService.getPagination(position, pageSize);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department) {
        Department tDepartment = new Department();
        tDepartment.setName(department.getName());
        tDepartment.setDescription(department.getDescription());
        departmentService.save(tDepartment);
        return department;
    }

    @GetMapping("/department/{id}")
    public Department get(@PathVariable int id) {
        Department department = departmentService.get(id);
        if (department == null) {
            throw new RuntimeException("Department not found for the Id:" + id);
        }
        return department;
    }

    @PutMapping("/department")
    public Department update(@RequestBody Department department) {
        departmentService.save(department);
        return department;
    }

    @DeleteMapping("/department/{id}")
    public String delete(@PathVariable int id) {
        departmentService.delete(id);
        return "Department has been deleted with id:" + id;
    }
}
