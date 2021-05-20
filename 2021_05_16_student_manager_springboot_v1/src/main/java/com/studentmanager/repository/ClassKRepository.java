package com.studentmanager.repository;
import java.util.List;
import java.util.Optional;

import com.studentmanager.model.ClassK;
import com.studentmanager.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassKRepository  extends JpaRepository<ClassK, String>{
    
}
