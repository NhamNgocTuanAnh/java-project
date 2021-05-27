package com.studentmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "student")
@DynamicUpdate
@Data
// @Builder
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
    private Date birthdate;
    @Column(name = "gender")
    private int gender;

    @Column(name = "class_id")
    private String class_id;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    @JsonIgnore
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
