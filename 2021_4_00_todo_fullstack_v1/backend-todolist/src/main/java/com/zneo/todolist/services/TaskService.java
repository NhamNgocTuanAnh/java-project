package com.zneo.todolist.services;

import java.util.Optional;

import com.zneo.todolist.Repositories.TaskRepository;
// import com.zneo.todolist.Repositories.TodolistRepository;
import com.zneo.todolist.form.TaskForm;
import com.zneo.todolist.models.Task;
// import com.zneo.todolist.models.TodoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    // @Autowired
    // private TodolistRepository todolistRepository;

    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }

    public Task createTask(TaskForm taskForm) {
        Task task = new Task();
        task.setComplete(false);
        task.setTitle(taskForm.getTitle());
        task.setContent(taskForm.getContent());
        // if(taskForm.getTodolist_id() != null){
        //     TodoList todoList = todolistRepository.findById(taskForm.getTodolist_id()).get();
        //     if(todoList!=null){
        //         task.setTodoList(todoList);
        //     }
        // }
        taskRepository.save(task);
       
        return task;
    }

    public Task editTask(Task editedTask) {
        Task task = taskRepository.findById(editedTask.getId()).orElse(null);
        if (task != null) {
            task.setTitle(editedTask.getTitle());
            return taskRepository.save(task);
        }
        // Create new if we dont have.
        return taskRepository.save(task);
    }
    public Task changeComplete(Long id) {
        Task task = taskRepository.findById(id).get( );
        if (task != null) {
            task.setComplete(!task.getComplete());
            taskRepository.save(task);
            return task;
        }
        return null;
    }
    public Boolean deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
            return true;
        }
        return false;
    }
}
