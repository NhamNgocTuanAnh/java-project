package com.zneo.todolist.controllers;
import com.zneo.todolist.models.Task;
import com.zneo.todolist.services.TaskService;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTodo(){
        return (ResponseEntity<?>) taskService.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "id") Long id){
        Task task = taskService.getOne(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }        return ResponseEntity.ok(task);
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task){
        return ResponseEntity.ok(task);
    }
    // @GetMapping("/todolist/{todolistID}/task/{taskID}")
    // public Task get
}
