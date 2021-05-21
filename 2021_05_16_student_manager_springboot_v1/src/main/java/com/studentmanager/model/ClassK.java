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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Class")

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
    private String departmentId;
    
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCOURSE() {
		return COURSE;
	}


	public void setCOURSE(String cOURSE) {
		COURSE = cOURSE;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Timestamp getTIME_CREATE() {
		return TIME_CREATE;
	}


	public void setTIME_CREATE(Timestamp tIME_CREATE) {
		TIME_CREATE = tIME_CREATE;
	}


	public Timestamp getTIME_UPDATE() {
		return TIME_UPDATE;
	}


	public void setTIME_UPDATE(Timestamp tIME_UPDATE) {
		TIME_UPDATE = tIME_UPDATE;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ClassK(String id, String name, String cOURSE, String description, String departmentId, Department department,
			Timestamp tIME_CREATE, Timestamp tIME_UPDATE) {
		super();
		this.id = id;
		this.name = name;
		COURSE = cOURSE;
		this.description = description;
		this.departmentId = departmentId;
		this.department = department;
		TIME_CREATE = tIME_CREATE;
		TIME_UPDATE = tIME_UPDATE;
	}


	public ClassK() {
		super();
	}
    
}
