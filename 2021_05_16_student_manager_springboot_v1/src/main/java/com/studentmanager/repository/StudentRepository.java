package com.studentmanager.repository;

import java.util.List;

import com.studentmanager.dto.StudentAnlDto;
import com.studentmanager.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT *"+ 
    " FROM student n where name is not null ORDER BY n.time_create desc"+ 
    " OFFSET (:pagenumber -1)*:rowsofpage ROWS FETCH NEXT :rowsofpage ROWS ONLY", nativeQuery = true)
    public List<Student> pageableStudent(@Param("pagenumber") int pagenumber,@Param("rowsofpage")  int rowsofpage);

    // @Query("SELECT e FROM Student e WHERE e.name LIKE :name ORDER BY e.id")
    // public List<Student> findByName1(@Param("name") String name, Pageable pageable);

    @Query(value = "select"+ 
    " new com.studentmanager.dto.StudentAnlDto(count(s.classId),c.name, d.name)"+
    " from Student s inner join ClassK c on s.classId=c.id inner join Department d on d.id=c.departmentId"+ 
    " where trunc(to_number(sysdate - to_date(s.birthdate)) / 365.25) BETWEEN ?1 and ?2 group by s.classId,c.name,d.name")
    public List<StudentAnlDto> analyList(int startAge,int endAge);   
    
    // @Query(value = "select * from Student s  where a.birthdate <= :creationDateTime ")
    // public List<Student> analyListDate(  Date creationDateTime);

    Page<Student> findByNameContaining(String name, Pageable pageable);

    Page<Student> findAll(Pageable pageable);

    List<Student> findAll();

}
