package com.studentmanager.controllers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;


import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.studentmanager.entities.Student;
import com.studentmanager.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class StudentController {
    @Autowired
	private StudentRepository studentRepository;
	@GetMapping("/student")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	@PostMapping("/student")
    public Student saveStudent(@RequestBody Student student) {
        Student st = new Student();
    
		student.setAddress(st.getAddress());
		student.setName(st.getName());
        studentRepository.save(st);
        return st;
    }
 
    @PutMapping("/student")
    public Student update(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }
    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return "student has been deleted with id:" + id;
    }
}
