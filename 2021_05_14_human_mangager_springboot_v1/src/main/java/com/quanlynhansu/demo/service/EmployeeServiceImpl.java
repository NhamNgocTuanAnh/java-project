package com.quanlynhansu.demo.service;

import com.quanlynhansu.demo.dao.EmployeeDao;
import java.util.List;

import com.quanlynhansu.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Override
    public List<Employee> get() {
        return employeeDao.get();
    }

    @Transactional
    @Override
    public Employee get(int id) {
        return employeeDao.get(id);
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        // TODO Auto-generated method stub
        employeeDao.save(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        employeeDao.delete(id);
    }

}
