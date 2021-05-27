package com.studentmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Class")
@Data
public class ClassK  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "COURSE")
    private String COURSE;
    
    @Column(name = "description")
    private String description;

    @Column(name = "Department_id")
    private String department_id;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	@JsonIgnore
    // @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})   
    @JoinColumn(name = "Department_id", insertable = false, updatable = false)
    private Department department;

    @Column(name = "TIME_CREATE", nullable = true, updatable = true)
    @CreatedDate
    private Timestamp TIME_CREATE;

   
    @Column(name = "TIME_UPDATE", nullable = true)
    @LastModifiedDate
    private Timestamp TIME_UPDATE;

    
}
