package com.quanlynhansu.demo.service;

import com.quanlynhansu.demo.entity.Department;

import java.util.List;

public interface DepartmentService {
  List<Department> get();

  Department get(int id);

  void save(Department department);

  void delete(int id);

  List<Department> getPagination(int position, int pageSize);
}
