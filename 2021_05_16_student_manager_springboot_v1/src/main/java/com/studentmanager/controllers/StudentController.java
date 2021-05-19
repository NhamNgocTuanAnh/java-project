package com.studentmanager.controllers;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.studentmanager.dtos.StudentDto;
import com.studentmanager.entities.ClassK;
import com.studentmanager.entities.Student;
import com.studentmanager.repository.ClassKRepository;
import com.studentmanager.repository.StudentRepository;

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
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassKRepository classKRepository;

    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    public Student saveStudent(@RequestBody Student studentDto) {
        Student student = new Student();

        String name = studentDto.getName();
        Date BIRTHDATE = studentDto.getBIRTHDATE();
        int gender = studentDto.getGender();
        String address = studentDto.getAddress();

        // ClassK classK=
        // classKRepository.findById(studentDto.getClassK().getId()).get();
        if (!classKRepository.findById(studentDto.getClassk().getId()).isPresent()) {
            return null;
        }
        if (student != null) {
            if (BIRTHDATE != null) {
                student.setBIRTHDATE(BIRTHDATE);
            }

            if (address != null) {
                student.setAddress(address);
            }
            if (studentDto.getClassk() != null) {
                student.setClassk(studentDto.getClassk());
            } else {
                return null;
            }
            if (name != null) {
                student.setName(name);
            }
            student.setGender(studentDto.getGender());
            student.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
            student.setTIME_CREATE(Timestamp.valueOf(LocalDateTime.now()));
            student.setId("id");
            studentRepository.save(student);
        }
        return studentDto;
    }

    @PutMapping("/student")
    public Student update(@RequestBody StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).get();

        String name = studentDto.getName();
        Date BIRTHDATE = studentDto.getBIRTHDATE();
        int gender = studentDto.getGender();
        String address = studentDto.getAddress();
        // ClassK classK = classKRepository.findById(studentDto.getClassK_id()).get();
        if (!classKRepository.findById(studentDto.getClassK().getId()).isPresent()) {
            return null;
        }
        if (student != null) {
            if (BIRTHDATE != null) {
                student.setBIRTHDATE(BIRTHDATE);
            }

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
            throw new RuntimeException("Employee not found for the Id:" + id);
        }
        return student;
    }
}
