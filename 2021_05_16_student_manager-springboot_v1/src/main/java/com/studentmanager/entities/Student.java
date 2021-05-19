package com.studentmanager.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student extends AuditModel {

    @Column(name = "name")
    private String name;

    @Column(name = "BIRTHDATE")
    private Date BIRTHDATE;
    @Column(name = "gender")
    private int gender;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    private Class class1;

    @Column(name = "address")
    private String address;

}
