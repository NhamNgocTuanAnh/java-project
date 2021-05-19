package com.quanlynhansu.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.quanlynhansu.demo.entity.Department;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.*;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Department> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Department> department = currentSession.createQuery("from Department", Department.class).getResultList();

        return department;
    }

    @Override
    public Department get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Department department = currentSession.get(Department.class, id);
        return department;
    }

    @Override
    public void save(Department department) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(department);

    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Department department = currentSession.get(Department.class, id);
        currentSession.delete(department);
    }

    @Override
    public List<Department> getPagination(int position, int pageSize) {
        Session currentSession = entityManager.unwrap(Session.class);
        

        TypedQuery<Department> idQuery = entityManager.createQuery("FROM department d order by d.id asc", Department.class);
        List<Department> departments = idQuery
                              .getResultList();
        // List<Department> departments = currentSession.createQuery("SELECT * FROM department d order by d.id asc", Department.class)
        //         .setFirstResult(position).setMaxResults(pageSize).getResultList();
        return departments;
    }

    @Override
    public long countTotalRecords() {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Long)currentSession.createQuery("Select count (d.id) from Department d").uniqueResult();       
    }

}
