package com.studentmanager.repository;

import com.studentmanager.model.ClassK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassKRepository  extends JpaRepository<ClassK, String>{
    
}
