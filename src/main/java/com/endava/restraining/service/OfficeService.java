package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.OfficeDAO;
import com.endava.restraining.entity.OfficeEntity;
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

    public void add(OfficeDto officeDto){
        OfficeEntity office = new OfficeEntity();
        office.setLocation(locationDAO.getById(officeDto.getLocationId()));
        officeDAO.save(office);
    }
    public List<OfficeDto> getAll(Long limit){
        List<OfficeDto> offices = new ArrayList<>();
        List<OfficeEntity> daoOffices = checkLimit(limit);

        for(OfficeEntity daoOffice : daoOffices) {
            OfficeDto office = officeToDto(daoOffice);
            offices.add(office);
        }
        return offices;
    }
    public void delete(Long id) {
        officeDAO.deleteById(id);
    }

    public void update(Long id, OfficeDto officeDto) {
        OfficeEntity office = officeDAO.getById(id);
        office.setLocation(locationDAO.getById(officeDto.getLocationId()));
        officeDAO.save(office);
    }
    public List<OfficeEntity> checkLimit(Long limit){
        if(limit == null){
            return officeDAO.findAll();
        }
        else {
            return officeDAO.findAll(limit);
        }
    }
    public OfficeDto officeToDto(OfficeEntity office){
        OfficeDto officeDto = new OfficeDto();
        officeDto.setId(office.getId());
        officeDto.setLocationId(office.getLocation().getId());
        return officeDto;
    }
}
