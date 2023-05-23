package com.endava.restraining.service;

import com.endava.restraining.dao.DepartmentDAO;
import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.entity.DepartmentEntity;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentDAO departmentDAO;
    private final UserDAO userDAO;

    public void add(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(departmentDto.getName());
        departmentDAO.save(department);
    }

    public List<DepartmentDto> getAll() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<DepartmentEntity> departmentEntities = departmentDAO.findAll();

        for (DepartmentEntity departmentEntity: departmentEntities) {
            DepartmentDto departmentDto = DepartmentDto.builder()
                    .id(departmentEntity.getId())
                    .name(departmentEntity.getName())
                    .build();
            departmentsDto.add(departmentDto);
        }

        return departmentsDto;
    }

    public void delete(Long id) {
        departmentDAO.deleteById(id);
    }

    public void update(Long id, DepartmentDto departmentDto) {
        DepartmentEntity departmentEntity = departmentDAO.getById(id);
        departmentEntity.setName(departmentDto.getName());
        departmentDAO.save(departmentEntity);
    }

    public void addUser(Long departmentId, Long userId) {
        DepartmentEntity departmentEntity = departmentDAO.getById(departmentId);
        UserEntity user = userDAO.getById(userId);
        departmentEntity.getUsers().add(user);
        departmentDAO.save(departmentEntity);
    }
}
