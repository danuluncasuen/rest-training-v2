package com.endava.restraining.entity.dto;

import com.endava.restraining.entity.LocationEntity;
import lombok.Data;

@Data
public class OfficeDto {
    private Long id;
    private String officeName;
    private Long locationId;
}
