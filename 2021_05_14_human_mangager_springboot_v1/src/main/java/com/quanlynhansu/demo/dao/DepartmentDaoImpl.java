package com.quanlynhansu.demo.dao;

import java.util.List;

import com.quanlynhansu.demo.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.*;
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

        List<Department> departments = currentSession.createQuery("FROM department",Department.class)
                .setFirstResult(position).setMaxResults(pageSize).getResultList();
        return departments;
    }

}
