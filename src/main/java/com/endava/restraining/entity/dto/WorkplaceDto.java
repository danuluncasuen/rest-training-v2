package com.endava.restraining.entity.dto;

import com.endava.restraining.entity.OfficeEntity;
import lombok.Data;

@Data
public class WorkplaceDto {
    private Long id;
    private Integer floor;
    private Long officeId;

}
