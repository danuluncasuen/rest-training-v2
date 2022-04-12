package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.OfficeDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.OfficeEntity;
import com.endava.restraining.entity.dto.LocationDto;
import com.endava.restraining.entity.dto.OfficeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfficeService {
    
    private final OfficeDAO officeDAO;
    private final LocationDAO locationDAO;

    public void add(OfficeDto officeDto) {
        LocationEntity location = locationDAO.getById(officeDto.getLocationId());
        OfficeEntity office = new OfficeEntity();
        office.setOfficeName(officeDto.getOfficeName());
        office.setLocation(location);
        officeDAO.save(office);
    }

    public List<OfficeDto> getAll(Long limit) {
        List<OfficeDto> officeDtoList = new ArrayList<>();
        List<OfficeEntity> daoOffices = officeDAO.findAll();
        if (limit != null) {
            for (int i=0; i<limit; i++) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setId(officeDtoList.get(i).getId());
                officeDto.setOfficeName(officeDtoList.get(i).getOfficeName());
                officeDto.setLocationId(officeDtoList.get(i).getLocationId());
                officeDtoList.add(officeDto);
                if (i == officeDtoList.size()-1) {
                    break;
                }
            }
        } else {
            for (OfficeEntity daoOffice : daoOffices) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setId(daoOffice.getId());
                officeDto.setOfficeName(daoOffice.getOfficeName());
                officeDto.setLocationId(daoOffice.getLocation().getId());
                officeDtoList.add(officeDto);
            }
        }
        return officeDtoList;
    }

    public void delete(Long id) {
        locationDAO.deleteById(id);
    }

    public void update(Long id, LocationDto locationDto) {
        LocationEntity location = locationDAO.getById(id);
        location.setCountry(locationDto.getCountry());
        location.setCity(locationDto.getCity());
        locationDAO.save(location);
    }
}
