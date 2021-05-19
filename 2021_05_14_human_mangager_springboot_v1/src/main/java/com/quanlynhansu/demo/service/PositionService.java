package com.quanlynhansu.demo.service;

import com.quanlynhansu.demo.entity.Employee;
import com.quanlynhansu.demo.entity.Position;

import java.util.List;

public interface PositionService {
  List<Position> get();

  Position get(int id);

  void save(Position position);

  void delete(int id);
  // @Autowired
  // private SessionFactory sessionFactory;

  // public List<Employee> findAll() {
  // Session session = this.sessionFactory.getCurrentSession();
  // return session.createQuery("FROM employee", Employee.class).getResultList();
  // }
  // public void save(final Employee employee) {
  // Session session = this.sessionFactory.getCurrentSession();
  // session.persist(employee);
  // }

  // public void update(final Employee employee) {
  // Session session = this.sessionFactory.getCurrentSession();
  // session.update(employee);
  // }

  // public Employee findById(final int id) {
  // Session session = this.sessionFactory.getCurrentSession();
  // return session.get(Employee.class, id);
  // }

  // public void delete(final Employee employee) {
  // Session session = this.sessionFactory.getCurrentSession();
  // session.remove(employee);
  // }

  // public List<Employee> findAll() {
  // Session session = this.sessionFactory.getCurrentSession();
  // return session.createQuery("FROM Employees", Employee.class).getResultList();
  // }
}
