package com.zneo.todolist.controllers;
import java.util.List;

import javax.persistence.PostRemove;

import com.zneo.todolist.models.Task;
import com.zneo.todolist.models.TodoList;
import com.zneo.todolist.services.TaskService;
import com.zneo.todolist.services.TodoListService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @PostMapping("/todolists")
    public TodoList createTodoList(@Valid @RequestBody TodoList todoList){
        return todoListService.save(todoList);
    }
    
    @GetMapping("/todolists")
    public ResponseEntity<?> getallTodoList(){
        List<TodoList> todoLists = todoListService.findAll();
        return ResponseEntity.ok(todoLists) ;
    }
}
