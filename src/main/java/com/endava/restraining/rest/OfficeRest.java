package com.endava.restraining.rest;

import com.endava.restraining.entity.dto.OfficeDto;
import com.endava.restraining.service.OfficeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/office")
public class OfficeRest {
    private final OfficeService officeService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewOffice(@RequestBody OfficeDto officeDto) {
        try {
            officeService.add(officeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllOffices(@RequestParam(required = false) Long limit) {
        try {
            return new ResponseEntity<>(officeService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOffice(@PathVariable Long id) {
        try {
            officeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOffice(@PathVariable Long id, @RequestBody OfficeDto officeDto) {
        try {
            officeService.update(id, officeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}