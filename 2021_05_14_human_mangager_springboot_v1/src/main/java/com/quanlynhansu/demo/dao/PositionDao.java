package com.quanlynhansu.demo.dao;

import java.util.List;

import com.quanlynhansu.demo.entity.Position;

public interface PositionDao {
    List<Position> get();

    Position get(int id);

    void save(Position position);

    void delete(int id);

}
