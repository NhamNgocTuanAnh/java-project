package com.quanlynhansu.demo.controller;
import java.util.List;

import com.quanlynhansu.demo.entity.Department;
import com.quanlynhansu.demo.entity.Employee;
import com.quanlynhansu.demo.entity.Position;
import com.quanlynhansu.demo.service.DepartmentService;
import com.quanlynhansu.demo.service.EmployeeService;
import com.quanlynhansu.demo.service.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PositionControler {
    
    @Autowired
    private PositionService positionService;

    @GetMapping("/position")
    public ResponseEntity<List<Position>> findAllPosition() {
        List<Position> positions = positionService.get();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @PostMapping("/position")
    public Position savePositon(@RequestBody Position position) {
        Position tPosition = new Position();
        tPosition.setName(position.getName());
        tPosition.setDescription(position.getDescription());
        positionService.save(tPosition);
        return tPosition;
    }

    @GetMapping("/position/{id}")
    public Position get(@PathVariable int id) {
        Position position = positionService.get(id);
        if (position == null) {
            throw new RuntimeException("Position not found for the Id:" + id);
        }
        return position;
    }

    @PutMapping("/position")
    public Position update(@RequestBody Position position) {
        positionService.save(position);
        return position;
    }

    @DeleteMapping("/position/{id}")
    public String delete(@PathVariable int id) {
        positionService.delete(id);
        return "Position has been deleted with id:" + id;
    }
}
