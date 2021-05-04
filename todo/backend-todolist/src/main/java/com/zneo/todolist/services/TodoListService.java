package com.zneo.todolist.services;

import com.zneo.todolist.models.TodoList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListService  extends JpaRepository<TodoList,Long>{
    
}
