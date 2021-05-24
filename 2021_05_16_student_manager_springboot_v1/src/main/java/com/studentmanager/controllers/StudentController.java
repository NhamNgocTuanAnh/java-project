package com.studentmanager.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.studentmanager.dto.StudentAnlDto;
import com.studentmanager.dto.StudentDto;
import com.studentmanager.model.ClassK;
import com.studentmanager.model.Student;
import com.studentmanager.repository.ClassKRepository;
import com.studentmanager.repository.StudentRepository;
import com.studentmanager.service.StudentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private ClassKRepository classKRepository;

    @Autowired
    private StudentService studentService;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/anal-student")
    public List<StudentAnlDto> getAllStudents(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "department_id", required = false) String department_id,
            @RequestParam(name = "class_id", required = false) String class_id,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(defaultValue = "0")  int startAge,
            @RequestParam(defaultValue = "25")  int endAge) {
            
        return studentRepository.findList(name, department_id, class_id,gender, startAge, endAge);
    }

    @GetMapping("/student")
    public ResponseEntity<Map<String, Object>> pageable(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {

        try {
            List<Student> students = studentService.pageableStudent(page, size);
            Map<String, Object> response = new HashMap<>();
            response.put("students", students);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto) {

        try {

            return new ResponseEntity<>(studentService.saveStudent(studentDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/student")
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).get();

        try {

            if (student == null) {
                return new ResponseEntity<>("Student not found.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(studentService.updateStudent(student, studentDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable String id) {
        studentRepository.deleteById(id);
        return "student has been deleted with id:" + id;
    }

    @GetMapping("/student/{id}")
    public Student get(@PathVariable String id) {
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            throw new RuntimeException("Student not found for the Id:" + id);
        }
        return student;
    }
}
