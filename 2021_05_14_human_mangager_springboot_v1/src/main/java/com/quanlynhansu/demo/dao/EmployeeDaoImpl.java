package com.quanlynhansu.demo.dao;

import com.quanlynhansu.demo.entity.Employee;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private EntityManager entityManager;

    // SessionFactory sessionFactory = new
    // Configuration().configure().buildSessionFactory();

    @Override
    public List<Employee> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Employee> employees = currentSession.createQuery("from Employee", Employee.class).getResultList();

        return employees;
    }

    @Override
    public Employee get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Employee employeeObj = currentSession.get(Employee.class, id);
        return employeeObj;

    }

    @Override
    public void save(Employee employee) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Employee employeeObj = currentSession.get(Employee.class, id);
        currentSession.delete(employeeObj);
    }

}
