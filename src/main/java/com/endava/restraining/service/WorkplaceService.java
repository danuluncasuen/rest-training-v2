package com.endava.restraining.service;

import com.endava.restraining.dao.OfficeDAO;
import com.endava.restraining.dao.WorkplaceDAO;
import com.endava.restraining.entity.OfficeEntity;
import com.endava.restraining.entity.WorkplaceEntity;
import com.endava.restraining.entity.dto.WorkplaceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class WorkplaceService {

    private final WorkplaceDAO workplaceDAO;
    private final OfficeDAO officeDAO;

    public void add(WorkplaceDto workplaceDto){
        WorkplaceEntity workplace = new WorkplaceEntity();
        workplace.setFloor(workplaceDto.getFloor());
        if(workplaceDto.getOfficeId() != null){
            OfficeEntity office = officeDAO.getById(workplaceDto.getOfficeId());
            workplace.setOffice(office);
        }
        workplaceDAO.save(workplace);
    }

    public List<WorkplaceDto> getAll(Long limit){
        List<WorkplaceDto> workplaces = new ArrayList<>();
        List<WorkplaceEntity> daoWorkplaces = workplaceDAO.findAll();
        if (limit != null) {
            for (int i = 0; i < limit; i++) {
                WorkplaceDto workplace = new WorkplaceDto();
                workplace.setId(daoWorkplaces.get(i).getId());
                workplace.setFloor(daoWorkplaces.get(i).getFloor());
                if(workplace.getOfficeId() != null) {
                    workplace.setOfficeId(daoWorkplaces.get(i).getOffice().getId());
                }
                workplaces.add(workplace);
                if (i == daoWorkplaces.size() - 1) {
                    break;
                }

            }
        } else {
            for(WorkplaceEntity daoWorkplace: daoWorkplaces){
                WorkplaceDto workplace = new WorkplaceDto();
                workplace.setId(daoWorkplace.getId());
                workplace.setFloor(daoWorkplace.getFloor());
                if(workplace.getOfficeId() != null) {
                    workplace.setOfficeId(daoWorkplace.getOffice().getId());
                }
                workplaces.add(workplace);
            }
        }
        return workplaces;
    }

    public void delete(Long id) {
        workplaceDAO.deleteById(id);
    }

    public void update(Long id, WorkplaceDto workplaceDto) {
        WorkplaceEntity workplace = workplaceDAO.getById(id);
        workplace.setFloor(workplaceDto.getFloor());
        if(workplaceDto.getOfficeId() != null){
            OfficeEntity office = officeDAO.getById(workplaceDto.getOfficeId());
            workplace.setOffice(office);
        }
        workplaceDAO.save(workplace);
    }
}
