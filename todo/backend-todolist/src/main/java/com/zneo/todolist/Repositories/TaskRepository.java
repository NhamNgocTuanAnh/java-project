package com.zneo.todolist.Repositories;


import java.util.Optional;

import com.zneo.todolist.models.Task;
// import com.zneo.todolist.models.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    Optional<Task> findById(Long Id);  
   
    Page<Task> findByComplete(boolean complete, Pageable pageable);
    Page<Task> findByTitleContaining(String title, Pageable pageable);
}