package com.zneo.todolist.models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task extends AuditModel {

    @Id // Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Giúp tự động tăng
    private Long id;

    private String title;
    private String content;
    private Boolean complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_id", nullable = true)
    @JsonIgnore
    private TodoList todoList;
}