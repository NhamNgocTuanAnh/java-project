package com.quanlynhansu.demo.dao;

import com.quanlynhansu.demo.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> get();

    Department get(int id);

    void save(Department department);

    void delete(int id);

    List<Department> getPagination(int position, int pageSize);

    public long countTotalRecords();
}
