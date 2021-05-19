package com.quanlynhansu.demo.service;

import com.quanlynhansu.demo.dao.DepartmentDao;
import java.util.List;

import com.quanlynhansu.demo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Transactional
    @Override
    public List<Department> get() {
        return departmentDao.get();
    }

    @Transactional
    @Override
    public Department get(int id) {
        return departmentDao.get(id);
    }

    @Transactional
    @Override
    public void save(Department department) {
        // TODO Auto-generated method stub
        departmentDao.save(department);
    }

    @Transactional
    @Override
    public void delete(int id) {
        departmentDao.delete(id);
    }

    @Override
    public List<Department> getPagination(int position, int pageSize) {
        // TODO Auto-generated method stub
        return  departmentDao.getPagination(position, pageSize);
    }

}
