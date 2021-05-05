package com.zneo.todolist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "todolists")
public class TodoList extends AuditModel{

    @Id // Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Giúp tự động tăng
    private Long id;

    private String project;

}
