package com.studentmanager.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.studentmanager.dtos.ClassKDto;
import com.studentmanager.entities.ClassK;
import com.studentmanager.entities.Department;
import com.studentmanager.repository.ClassKRepository;
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
public class ClassKController {
    @Autowired
    private ClassKRepository classKRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/classk")
    public ClassK saveStudent(@RequestBody ClassKDto classKDto) {
        ClassK classK = new ClassK();

        classK.setDepartment(departmentRepository.findById(classKDto.getDepartment_id()).get());
        classK.setDescription(classKDto.getDescription());
        classK.setName(classKDto.getName());
        classK.setCOURSE(classKDto.getCOURSE());
        classK.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
        classK.setTIME_CREATE(Timestamp.valueOf(LocalDateTime.now()));

        classKRepository.saveAndFlush(classK);
        return classK;

    }

    @PutMapping("/classk")
    public ClassK updateStudent(@RequestBody ClassKDto classKDto) {
        ClassK classK = classKRepository.findById(classKDto.getId()).get();
        String name = classKDto.getName();
        String COURSE = classKDto.getCOURSE();
        String description = classKDto.getDescription();
        Department department = departmentRepository.findById(classKDto.getDepartment_id()).get();
        if (classK != null) {
            if (name != null) {
                classK.setName(name);
            }
            if (COURSE != null) {
                classK.setCOURSE(COURSE);
            }
            if (description != null) {
                classK.setDescription(description);
            }
            if (department != null) {
                classK.setDepartment(department);
            }
            classK.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
            classKRepository.save(classK);

        }
        return classK;

    }

    @GetMapping("/classk")
    public List<ClassK> getAllDepartments() {
        return classKRepository.findAll();
    }

    @DeleteMapping("/classk/{id}")
    public String delete(@PathVariable String id) {
        classKRepository.deleteById(id);
        return "classk has been deleted with id:" + id;
    }

}
