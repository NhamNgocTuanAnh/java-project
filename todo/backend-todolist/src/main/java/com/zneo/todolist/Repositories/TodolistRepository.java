package com.zneo.todolist.Repositories;


import java.util.Optional;

import com.zneo.todolist.models.Task;
import com.zneo.todolist.models.TodoList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodolistRepository  extends JpaRepository<TodoList,Long>{
    // Optional<Task> findById(Long Id);  
    // Optional<Task> findByIdAndTodoListContaining(Long Id,TodoList todolist);
  
}