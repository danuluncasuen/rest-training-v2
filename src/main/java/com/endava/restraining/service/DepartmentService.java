package com.endava.restraining.service;

import com.endava.restraining.dao.DepartmentDAO;
import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.entity.DepartmentEntity;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.dto.DepartmentDto;
import com.endava.restraining.entity.dto.LocationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentDAO departmentDAO;
    private  final UserDAO userDAO;

    public void add(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(departmentDto.getName());
        departmentDAO.save(department);
    }

    public void assigneeUserToDepartment(Long id, Long usersID) {
        UserEntity user = userDAO.getById(usersID);
        DepartmentEntity department = departmentDAO.getById(id);
        if(department.getUsers() == null) {
            department.setUsers(new ArrayList<>());
        }
        department.getUsers().add(user);
        departmentDAO.save(department);
    }


    public List<DepartmentDto> getAll(Long limit) {
        List<DepartmentDto> departments = new ArrayList<>();
        List<DepartmentEntity> daoDepartments = departmentDAO.findAll();
        if (limit != null) {
            for (int i=0; i<limit; i++) {
                DepartmentDto department = new DepartmentDto();
                department.setId(daoDepartments.get(i).getId());
                department.setName(daoDepartments.get(i).getName());
                departments.add(department);
                if (i == daoDepartments.size()-1) {
                    break;
                }
            }
        } else {
            for (DepartmentEntity daoDepartment : daoDepartments) {
                DepartmentDto department = new DepartmentDto();
                department.setId(daoDepartment.getId());
                department.setName(daoDepartment.getName());
                departments.add(department);
            }
        }
        return departments;
    }

    public void delete(Long id) {
        departmentDAO.deleteById(id);
    }

    public void update(Long id, DepartmentDto departmentDto) {
        DepartmentEntity department = departmentDAO.getById(id);
        department.setName(departmentDto.getName());
        departmentDAO.save(department);
    }

}
