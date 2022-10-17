package com.endava.restraining.rest;

import com.endava.restraining.entity.dto.DepartmentDisciplineNameDTO;
import com.endava.restraining.entity.dto.DisciplineDTO;
import com.endava.restraining.service.DisciplineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/discipline")
public class DisciplineRest {
    private final DisciplineService disciplineService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        try {
            disciplineService.addDiscipline(disciplineDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllDisciplines(@RequestParam(required = false) Long limit) {
        try {
            return new ResponseEntity<>(disciplineService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assignDepartment")
    public ResponseEntity<Object> assignDepartment(@RequestBody DepartmentDisciplineNameDTO departmentDisciplineNameDTO) {
        try {
            disciplineService.assignDepartmentToDiscipline(departmentDisciplineNameDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDiscipline(@PathVariable Long id) {
        try {
            disciplineService.deleteDiscipline(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDTO disciplineDTO) {
        try {
            disciplineService.updateDiscipline(id, disciplineDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
