package com.quanlynhansu.demo.controller;

import java.util.List;

import com.quanlynhansu.demo.entity.Employee;
import com.quanlynhansu.demo.service.EmployeeService;

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
public class EmployeeControler {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        List<Employee> employees = employeeService.get();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employeeObj) {
        Employee em = new Employee();
        em.setDepartment(employeeObj.getDepartment());
        em.setPosition(employeeObj.getPosition());
        em.setFirst_name(employeeObj.getFirst_name());
        em.setLast_name(employeeObj.getLast_name());
        employeeService.save(em);
        return em;
    }

    @GetMapping("/employee/{id}")
    public Employee get(@PathVariable int id) {
        Employee employeeObj = employeeService.get(id);
        if (employeeObj == null) {
            throw new RuntimeException("Employee not found for the Id:" + id);
        }
        return employeeObj;
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employeeObj) {
        employeeService.save(employeeObj);
        return employeeObj;
    }

    @DeleteMapping("/employee/{id}")
    public String delete(@PathVariable int id) {
        employeeService.delete(id);
        return "Employee has been deleted with id:" + id;
    }
}
