package com.zneo.todolist.controllers;

import com.zneo.todolist.Repositories.TaskRepository;
import com.zneo.todolist.form.TaskForm;
import com.zneo.todolist.models.Task;
import com.zneo.todolist.services.TaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  private Sort.Direction getSortDirection(String direction) {
    if (direction.equals("asc")) {
      return Sort.Direction.ASC;
    } else if (direction.equals("desc")) {
      return Sort.Direction.DESC;
    }

    return Sort.Direction.ASC;
  }

  @GetMapping("/tasks")
  public ResponseEntity<Map<String, Object>> getAllTasks(@RequestParam(required = false) String title,
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size,
      @RequestParam(defaultValue = "createdAt,desc") String[] sort) {

    try {
      List<Order> orders = new ArrayList<Order>();

      if (sort[0].contains(",")) {
        // will sort more than 2 fields
        // sortOrder="field, direction"
        for (String sortOrder : sort) {
          String[] _sort = sortOrder.split(",");
          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
        }
      } else {
        // sort=[field, direction]
        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
      }

      List<Task> tasks = new ArrayList<Task>();
      Pageable paging = PageRequest.of(page, size, Sort.by(orders));

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
  public ResponseEntity<Task> getTask(@PathVariable(value = "id") Long id) {
    Task task = taskService.getTask(id);
    if (task == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(task);
  }

  @PostMapping("/tasks")
  public ResponseEntity<Task> createTask(@RequestBody TaskForm taskForm) {
    return ResponseEntity.ok(taskService.createTask(taskForm));
  }

  @PutMapping("/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
    Optional<Task> taskData = taskRepository.findById(id);

    if (taskData.isPresent()) {
      Task _task = taskData.get();
      _task.setTitle(task.getTitle());
      _task.setContent(task.getContent());
      _task.setComplete(task.getComplete());
      return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Boolean> deleteTask(@PathVariable(value = "id") Long id) {
    return ResponseEntity.ok(taskService.deleteTask(id));
  }

  @DeleteMapping("/tasks")
  public ResponseEntity<HttpStatus> deleteAllTasks() {
    try {
      taskRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
