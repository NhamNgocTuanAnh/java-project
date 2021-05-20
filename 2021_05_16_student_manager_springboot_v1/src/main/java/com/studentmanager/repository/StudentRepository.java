package com.studentmanager.repository;

import java.util.List;
import com.studentmanager.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {



    @Query(value ="SELECT *  FROM student where name is not null ORDER BY time_create desc OFFSET (?1 -1)*?2 ROWS FETCH NEXT ?2 ROWS ONLY", nativeQuery = true)
    public List<Student> pageableStudent(int pagenumber,int rowsofpage);


    @Query("SELECT e FROM Student e WHERE e.name LIKE :name ORDER BY e.id")
    public List<Student> findByName1(@Param("name") String name, Pageable pageable);

    @Query("SELECT e FROM Student e WHERE e.name LIKE :name ORDER BY e.id")
    public List<Student> analyList();


    Page<Student> findByNameContaining(String name, Pageable pageable);

    Page<Student> findAll(Pageable pageable);
  
    List<Student> findAll();
  
}
