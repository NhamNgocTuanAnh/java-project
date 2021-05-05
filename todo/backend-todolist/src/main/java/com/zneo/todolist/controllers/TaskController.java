package com.zneo.todolist.controllers;
import com.zneo.todolist.Repositories.TaskRepository;
import com.zneo.todolist.form.TaskForm;
import com.zneo.todolist.models.Task;
import com.zneo.todolist.services.TaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ResponseEntity<Map<String, Object>> getAllTasks(
          @RequestParam(required = false) String title,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "3") int size
        ) {
  
      try {
        List<Task> tasks = new ArrayList<Task>();
        Pageable paging = PageRequest.of(page, size);
        
        Page<Task> pageTuts;
        if (title == null)
          pageTuts = taskRepository.findAll(paging);
        else
          pageTuts = taskRepository.findByTitleContaining(title, paging);
  
          tasks = pageTuts.getContent();
  
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", tasks);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
  
        return new ResponseEntity<>(response, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "id") Long id){
        Task task = taskService.getTask(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }        return ResponseEntity.ok(task);
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody TaskForm taskForm){
        return ResponseEntity.ok(taskService.createTask(taskForm));
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
    // @GetMapping("/todolist/{todolistID}/task/{taskID}")
    // public Task get
}
