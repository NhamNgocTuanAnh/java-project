package com.zneo.todolist.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "todolists")
public class TodoList extends AuditModel{

    @Id // Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Giúp tự động tăng
    private Long id;

    private String project;

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "todolist")
    // private Set<Task> tasks = new HashSet<>();
    // @OneToMany(mappedBy = "todolist")
    // private Set<Task> tasks = new HashSet<>();;
}
