package com.studentmanager.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<StudentAnlDto> getAllStudents(@RequestParam(defaultValue = "20") int startAge,
            @RequestParam(defaultValue = "25") int endAge) {

        return studentRepository.analyList(startAge, endAge);
    }

    @GetMapping("/student")
    public ResponseEntity<Map<String, Object>> pageable(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

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
    public Student saveStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();

        String name = studentDto.getName();
        Date BIRTHDATE = studentDto.getBIRTHDATE();
        int gender = studentDto.getGender();
        String address = studentDto.getAddress();

        // classKRepository.findById(studentDto.getClassK().getId()).get();
        if (!classKRepository.findById(studentDto.getClassk_id()).isPresent()) {
            return null;
        } else {
            student.setClassId(studentDto.getClassk_id());
        }
        student.setId("1");

        if (BIRTHDATE != null) {
            student.setBIRTHDATE(BIRTHDATE);
        }

        if (address != null) {
            student.setAddress(address);
        }

        if (name != null) {
            student.setName(name);
        }
        student.setGender(studentDto.getGender());
        student.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
        student.setTIME_CREATE(Timestamp.valueOf(LocalDateTime.now()));
        student.setId("id");
        studentRepository.save(student);

        return student;
    }

    @PutMapping("/student")
    public Student update(@RequestBody StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).get();

        String name = studentDto.getName();
        Date BIRTHDATE = studentDto.getBIRTHDATE();
        int gender = studentDto.getGender();
        String address = studentDto.getAddress();
        // ClassK classK = classKRepository.findById(studentDto.getClassK_id()).get();
        if (!classKRepository.findById(studentDto.getClassk_id()).isPresent()) {
            return null;
        }
        if (student != null) {
            // if (BIRTHDATE != null) {
            // student.setBIRTHDATE(BIRTHDATE);
            // }

            if (address != null) {
                student.setAddress(address);
            }
            // if (classK != null) {
            // student.setClassk(classK);
            // }
            if (name != null) {
                student.setName(name);
            }
            student.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));

            studentRepository.save(student);
        }

        return student;
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
