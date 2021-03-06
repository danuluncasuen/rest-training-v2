package com.endava.restraining.rest;

import com.endava.restraining.entity.dto.WorkplaceDto;
import com.endava.restraining.service.WorkplaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/workplace")
public class WorkplaceRest {
    private final WorkplaceService workplaceService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewWorkplace(@RequestBody WorkplaceDto workplaceDto){
        try {
            workplaceService.add(workplaceDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllWorkplaces(@RequestParam(required = false) Long limit) {
        try {
            return new ResponseEntity<>(workplaceService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
