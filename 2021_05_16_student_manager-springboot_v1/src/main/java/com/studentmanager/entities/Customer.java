package com.studentmanager.entities;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")
    Long id;

    String name;
    String email;
}
