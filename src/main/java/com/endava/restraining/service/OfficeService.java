package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.OfficeDAO;
import com.endava.restraining.entity.OfficeEntity;
import com.endava.restraining.entity.dto.OfficeDto;
import com.endava.restraining.util.OfficeConverter;
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
        List<OfficeEntity> daoOffices = officeDAO.findAll();

        if (limit != null) {
            for (int i=0; i<limit; i++) {
                OfficeDto office = OfficeConverter.officeToDto(daoOffices.get(i));
                offices.add(office);
                if (i == daoOffices.size()-1) {
                    break;
                }
            }
        } else {
            for (OfficeEntity daoOffice : daoOffices) {
                OfficeDto office = OfficeConverter.officeToDto(daoOffice);
                offices.add(office);
            }
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
}
