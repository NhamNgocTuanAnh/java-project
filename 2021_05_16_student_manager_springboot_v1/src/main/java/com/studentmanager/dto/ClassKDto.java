package com.studentmanager.dto;

import com.studentmanager.model.Department;

import lombok.Data;


public class ClassKDto {
    private String id;
    private String name;
    private String COURSE;
    private String description;
    private String department_id;
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
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public ClassKDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassKDto(String id, String name, String cOURSE, String description, String department_id) {
		super();
		this.id = id;
		this.name = name;
		COURSE = cOURSE;
		this.description = description;
		this.department_id = department_id;
	}

    
    
}
