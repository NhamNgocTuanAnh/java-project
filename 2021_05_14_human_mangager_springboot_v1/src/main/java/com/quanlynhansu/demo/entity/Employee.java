package com.quanlynhansu.demo.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEE")
@Data // lombok giúp generate các hàm constructor, get, set v.v.
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "first_name")
    private String first_name;
    // Many to One Có nhiều người ở 1 phòng ban.
    @ManyToOne
    @JoinColumn(name = "department_id") // thông qua khóa ngoại department_id
    private Department department;

    // Many to One Có nhiều người ở 1 phòng ban.
    @ManyToOne
    @JoinColumn(name = "position_id") // thông qua khóa ngoại department_id
    private Position position;

}
