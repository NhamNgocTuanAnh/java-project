package com.zneo.todolist.services;
import java.util.Optional;

import com.zneo.todolist.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskService extends JpaRepository<Task,Long>{
    Optional<Task> findByIdandTodolistId(Long Id,Long todolistId);
}
