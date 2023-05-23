package com.endava.restraining.service;

import com.endava.restraining.dao.WorkplaceDAO;
import com.endava.restraining.entity.WorkplaceEntity;
import com.endava.restraining.entity.dto.WorkplaceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkplaceService {
    private final WorkplaceDAO workplaceDAO;

    public List<WorkplaceDto> getAll(Long limit) {
        List<WorkplaceEntity> workplaces = workplaceDAO.findAll();
        List<WorkplaceEntity> limitedWorkplaces;

        if (limit != null && limit < workplaces.size()) {
            limitedWorkplaces = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                limitedWorkplaces.add(workplaces.get(i));
            }
        } else {
            limitedWorkplaces = workplaces;
        }

        return convertToDTOList(limitedWorkplaces);
    }


    public void add(WorkplaceDto workplaceDTO) {
        WorkplaceEntity workplaceEntity = convertToEntity(workplaceDTO);
        workplaceDAO.save(workplaceEntity);
    }

    public void update(Long id, WorkplaceDto updatedWorkplaceDTO) throws Exception {
        Optional<WorkplaceEntity> workplace = workplaceDAO.findById(id);

        if (workplace.isPresent()) {
            WorkplaceEntity workplaceDataToUpdate = workplace.get();
            if (updatedWorkplaceDTO.getOffice() != null) {
                workplaceDataToUpdate.setOffice(updatedWorkplaceDTO.getOffice());
            }
            if (updatedWorkplaceDTO.getFloor() != null) {
                workplaceDataToUpdate.setFloor(updatedWorkplaceDTO.getFloor());
            }
            workplaceDAO.save(workplaceDataToUpdate);
        } else {
            throw new Exception("Workplace not found");
        }
    }

    public void delete(Long id) throws Exception {
        Optional<WorkplaceEntity> workplace = workplaceDAO.findById(id);

        if (workplace.isPresent()) {
            workplaceDAO.deleteById(id);
        } else {
            throw new Exception("Workplace not found");
        }
    }

    private WorkplaceDto convertToDTO(WorkplaceEntity workplaceEntity) {
        WorkplaceDto workplaceDTO = new WorkplaceDto();
        workplaceDTO.setId(workplaceEntity.getId());
        workplaceDTO.setOffice(workplaceEntity.getOffice());
        workplaceDTO.setFloor(workplaceEntity.getFloor());
        return workplaceDTO;
    }

    private List<WorkplaceDto> convertToDTOList(List<WorkplaceEntity> workplaceEntities) {
        return workplaceEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private WorkplaceEntity convertToEntity(WorkplaceDto workplaceDTO) {
        WorkplaceEntity workplaceEntity = new WorkplaceEntity();
        workplaceEntity.setOffice(workplaceDTO.getOffice());
        workplaceEntity.setFloor(workplaceDTO.getFloor());
        return workplaceEntity;
    }
}
