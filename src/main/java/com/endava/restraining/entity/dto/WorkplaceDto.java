package com.endava.restraining.entity.dto;

import com.endava.restraining.entity.OfficeEntity;
import lombok.Data;

@Data
public class WorkplaceDto {
    private Long id;
    private OfficeEntity office;

    private Integer floor;
}
