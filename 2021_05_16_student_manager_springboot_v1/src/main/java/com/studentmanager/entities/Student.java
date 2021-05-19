package com.studentmanager.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "BIRTHDATE")
    private Date BIRTHDATE;
    @Column(name = "gender")
    private int gender;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})   
    private ClassK classk;

    @Column(name = "address")
    private String address;

    @Column(name = "TIME_CREATE", nullable = true, updatable = true)
    @CreatedDate
    private Timestamp TIME_CREATE;

    @Column(name = "TIME_UPDATE", nullable = true)
    @LastModifiedDate
    private Timestamp TIME_UPDATE;

}
