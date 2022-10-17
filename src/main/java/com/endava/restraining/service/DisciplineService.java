package com.endava.restraining.service;

import com.endava.restraining.dao.DepartmentDAO;
import com.endava.restraining.dao.DisciplineDAO;
import com.endava.restraining.entity.DepartmentEntity;
import com.endava.restraining.entity.DisciplineEntity;
import com.endava.restraining.entity.dto.DepartmentDisciplineNameDTO;
import com.endava.restraining.entity.dto.DisciplineDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DisciplineService {

    private final DisciplineDAO disciplineDAO;
    private final DepartmentDAO departmentDAO;

    public void addDiscipline(DisciplineDTO disciplineDTO) {
        DisciplineEntity discipline = new DisciplineEntity();
        discipline.setName(disciplineDTO.getName());
        disciplineDAO.save(discipline);
    }

    public List<DisciplineDTO> getAll(Long limit) {
        List<DisciplineDTO> disciplines = new ArrayList<>();
        List<DisciplineEntity> daoDisciplines = disciplineDAO.findAll();
        if (limit != null) {
            for (int i = 0; i < limit; i++) {
                DisciplineDTO discipline = new DisciplineDTO();
                discipline.setId(daoDisciplines.get(i).getId());
                discipline.setName(daoDisciplines.get(i).getName());
                disciplines.add(discipline);
                if (i == daoDisciplines.size() - 1) {
                    break;
                }
            }
        } else {
            for (DisciplineEntity daoDiscipline : daoDisciplines) {
                DisciplineDTO discipline = new DisciplineDTO();
                discipline.setId(daoDiscipline.getId());
                discipline.setName(daoDiscipline.getName());
                discipline.setListOfIdDepartments(daoDiscipline.getDepartmentEntityList()
                        .stream()
                        .map(departmentEntity -> departmentEntity.getId())
                        .collect(Collectors.toList()));
                disciplines.add(discipline);
            }
        }
        return disciplines;
    }

    public void assignDepartmentToDiscipline(DepartmentDisciplineNameDTO departmentDisciplineNameDTO) {

        DisciplineEntity disciplineEntity = disciplineDAO.getDisciplineEntityByName(departmentDisciplineNameDTO.getDisciplineName());
        DepartmentEntity departmentEntity = departmentDAO.getDepartmentEntityByName(departmentDisciplineNameDTO.getDepartmentName());
        if (disciplineEntity.getDepartmentEntityList() == null) {
            disciplineEntity.setDepartmentEntityList(new ArrayList<>());
        }
        disciplineEntity.getDepartmentEntityList().add(departmentEntity);
        disciplineDAO.save(disciplineEntity);
    }

    public void deleteDiscipline(Long disciplineId) {
        disciplineDAO.deleteById(disciplineId);
    }

    public void updateDiscipline(Long disciplineId, DisciplineDTO disciplineDTO) {
        DisciplineEntity discipline = disciplineDAO.getById(disciplineId);
        if (disciplineDTO.getName() != null) discipline.setName(disciplineDTO.getName());
        if (discipline.getDepartmentEntityList() != null) {
            discipline.setDepartmentEntityList(
                    disciplineDTO.getListOfIdDepartments()
                            .stream()
                            .map(idDep -> departmentDAO.getById(idDep))
                            .collect(Collectors.toList()));
        }
        disciplineDAO.save(discipline);
    }
}

