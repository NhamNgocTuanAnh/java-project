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

        @Query(value = "SELECT *" + " FROM student n where name is not null ORDER BY n.time_create desc"
                        + " OFFSET (:pagenumber -1)*:rowsofpage ROWS FETCH NEXT :rowsofpage ROWS ONLY", nativeQuery = true)
        public List<Student> pageableStudent(@Param("pagenumber") int pagenumber, @Param("rowsofpage") int rowsofpage);

        @Query(value = "select" + " new com.studentmanager.dto.StudentAnlDto(count(s.class_id),c.name, d.name)"
                        + " from Student s inner join ClassK c on s.class_id=c.id inner join Department d on d.id=c.department_id"
                        + " where trunc(to_number(sysdate - to_date(s.birthdate)) / 365.25) BETWEEN ?1 and ?2 group by s.class_id,c.name,d.name")
        public List<StudentAnlDto> analyList(int startAge, int endAge);

        @Query(value = "select" + " new com.studentmanager.dto.StudentAnlDto(count(s.class_id),c.name, d.name)"
                        + " from Student s inner join ClassK c on s.class_id=c.id inner join Department d on d.id=c.department_id"
                        + " where ((:name IS NULL) or (s.name like %:name%) )"
                        + " and ((:department_id IS NULL)or(d.id = :department_id))"
                        + " and ((:class_id IS NULL)or(c.id= :class_id) )"
                        + " and ((:gender IS NULL)or(s.gender = TO_NUMBER(:gender)))"
                        + " and ((:startAge is NULL) or (trunc(to_number(sysdate - to_date(s.birthdate)) / 365.25)"
                        + " BETWEEN :startAge and :endAge))" + " group by s.class_id,c.name,d.name")
        public List<StudentAnlDto> findList(@Param("name") String name, @Param("department_id") String department_id,
                        @Param("class_id") String class_id, @Param("gender") String gender,
                        @Param("startAge") int startAge, @Param("endAge") int endAge);

        Page<Student> findByNameContaining(String name, Pageable pageable);

        Page<Student> findAll(Pageable pageable);

        List<Student> findAll();

}
