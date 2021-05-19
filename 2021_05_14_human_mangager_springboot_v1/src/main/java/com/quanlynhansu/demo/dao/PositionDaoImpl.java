package com.quanlynhansu.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.quanlynhansu.demo.entity.Position;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDaoImpl implements PositionDao{
    @Autowired
    private EntityManager entityManager;

    // SessionFactory sessionFactory = new
    // Configuration().configure().buildSessionFactory();

    @Override
    public List<Position> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Position> positions = currentSession.createQuery("from Position", Position.class).getResultList();

        return positions;
    }

    @Override
    public Position get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Position position = currentSession.get(Position.class, id);
        return position;

    }

    @Override
    public void save(Position position) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(position);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Position position = currentSession.get(Position.class, id);
        currentSession.delete(position);
    }

}
