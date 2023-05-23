package com.endava.restraining.util;

import com.endava.restraining.entity.OfficeEntity;
import com.endava.restraining.entity.dto.OfficeDto;

public class OfficeConverter {
    public static OfficeDto officeToDto(OfficeEntity office){
        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(office.getId());
        officeDto.setLocationId(office.getLocation().getId());
        return officeDto;
    }
}
