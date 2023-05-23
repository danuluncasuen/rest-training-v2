package com.endava.restraining.entity.dto;

import com.endava.restraining.entity.WorkplaceEntity;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
    private WorkplaceEntity workplaceId;
}