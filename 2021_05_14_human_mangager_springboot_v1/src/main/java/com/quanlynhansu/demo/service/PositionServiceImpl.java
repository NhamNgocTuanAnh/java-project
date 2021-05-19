package com.quanlynhansu.demo.service;

import com.quanlynhansu.demo.dao.EmployeeDao;
import com.quanlynhansu.demo.dao.PositionDao;

import java.util.List;

import com.quanlynhansu.demo.entity.Employee;
import com.quanlynhansu.demo.entity.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Transactional
    @Override
    public List<Position> get() {
        return positionDao.get();
    }

    @Transactional
    @Override
    public Position get(int id) {
        return positionDao.get(id);
    }

    @Transactional
    @Override
    public void save(Position position) {
        // TODO Auto-generated method stub
        positionDao.save(position);
    }

    @Transactional
    @Override
    public void delete(int id) {
        positionDao.delete(id);
    }

}
