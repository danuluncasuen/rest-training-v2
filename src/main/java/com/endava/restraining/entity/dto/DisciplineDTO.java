package com.endava.restraining.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class DisciplineDTO {
    private Long id;
    private String name;
    private List<Long> listOfIdDepartments;


}
