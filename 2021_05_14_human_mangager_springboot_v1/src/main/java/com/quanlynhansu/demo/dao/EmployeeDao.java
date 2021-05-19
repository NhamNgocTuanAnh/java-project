package com.quanlynhansu.demo.dao;

import com.quanlynhansu.demo.entity.Employee;

import java.util.List;


// @Repository
// @Transactional(rollbackFor = Exception.class)
public interface EmployeeDao {
  List<Employee> get();

  Employee get(int id);

  void save(Employee employee);

  void delete(int id);
 
}
