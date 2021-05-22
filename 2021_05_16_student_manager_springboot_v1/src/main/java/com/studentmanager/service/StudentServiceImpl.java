package com.studentmanager.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.studentmanager.dto.StudentAnlDto;
import com.studentmanager.dto.StudentDto;
import com.studentmanager.model.Student;
import com.studentmanager.repository.ClassKRepository;
import com.studentmanager.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassKRepository classKRepository;

    @Override
    public List<Student> pageableStudent(int pagenumber, int rowsofpage) {
        return studentRepository.pageableStudent(pagenumber, rowsofpage);
    }

    @Override
    public List<StudentAnlDto> analyList(int startAge, int endAge) {
        // TODO Auto-generated method stub
        return studentRepository.analyList(startAge, endAge);
    }

    @Override
    public void saveStudent(StudentDto studentDto) {
        // TODO Auto-generated method stub

        // Student student =
        // Student.builder().name(studentDto.getName()).birthdate(studentDto.getBirthdate())
        // .gender(studentDto.getGender()).TIME_CREATE(Timestamp.valueOf(LocalDateTime.now()))
        // .TIME_UPDATE(Timestamp.valueOf(LocalDateTime.now())).build();

        // studentRepository.save(student);
        Student student = new Student();
        String name = studentDto.getName();
        Date BIRTHDATE = studentDto.getBirthdate();
        int gender = studentDto.getGender();
        String address = studentDto.getAddress();
        // ClassK classK = classKRepository.findById(studentDto.getClassK_id()).get();
        if (!classKRepository.findById(studentDto.getClass_id()).isPresent()) {

        } else {

            if (address != null) {
                student.setAddress(address);
            }

            if (name != null) {
                student.setName(name);
            }
            student.setTIME_UPDATE(Timestamp.valueOf(LocalDateTime.now()));
            student.setTIME_CREATE(Timestamp.valueOf(LocalDateTime.now()));

            studentRepository.save(student);

        }

    }
}
