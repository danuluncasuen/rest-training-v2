package com.endava.restraining.rest;

import com.endava.restraining.entity.dto.DepartmentDto;
import com.endava.restraining.entity.dto.LocationDto;
import com.endava.restraining.service.DepartmentService;
import com.endava.restraining.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentRest {

    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            departmentService.add(departmentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/{departmentId}/{userId}")
    public ResponseEntity<Object> assigneeUserToDepartment(@PathVariable Long departmentId, @PathVariable Long userId) {
        try {
            departmentService.assigneeUserToDepartment(departmentId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllDepartments(@RequestParam(required = false) Long limit) {
        try {
            return new ResponseEntity<>(departmentService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        try {
            departmentService.update(id, departmentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
