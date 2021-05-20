package com.zneo.todolist.Repositories;


import java.util.List;
import java.util.Optional;

import com.zneo.todolist.models.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task,Long>{
    Optional<Task> findById(Long Id);  
   
    Page<Task> findByComplete(boolean complete, Pageable pageable);
    Page<Task> findByTitleContaining(String title, Pageable pageable);
  
  
    List<Task> findByTitleContaining(String title, Sort sort);
}