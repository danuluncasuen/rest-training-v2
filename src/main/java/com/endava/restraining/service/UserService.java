package com.endava.restraining.service;

import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.dao.WorkplaceDAO;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.WorkplaceEntity;
import com.endava.restraining.entity.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    //trebuie sa asum ca sunt oficii\
    private final UserDAO userDAO;
    private final WorkplaceDAO workPlaceDAO;

    public void add(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setWorkplace(userDto.getWorkplaceId());
        userDAO.save(user);
    }

    public List<UserDto> getAll(Long limit) {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> daoUsers = userDAO.findAll();
        if (limit != null) {
            for (int i = 0; i < limit; i++) {
                UserDto userDto = new UserDto();
                userDto.setId(daoUsers.get(i).getId());
                userDto.setName(daoUsers.get(i).getName());
                userDto.setAge(daoUsers.get(i).getAge());
                users.add(userDto);
                if (i == daoUsers.size() - 1) {
                    break;
                }
            }
        } else {
            for (UserEntity daoUs : daoUsers) {
                UserDto userDto2 = new UserDto();
                userDto2.setId(daoUs.getId());
                userDto2.setName(daoUs.getName());
                userDto2.setAge(daoUs.getAge());
                users.add(userDto2);
            }
        }
        return users;
    }

    public void delete(Long id) {
        userDAO.deleteById(id);
    }

    public void addUserWorkplace(Long workPlaceId, Long entityUserId) {
        WorkplaceEntity workplace = workPlaceDAO.getById(workPlaceId);
        UserEntity userEntity = userDAO.getById(entityUserId);
        userEntity.setWorkplace(workplace);
        userDAO.save(userEntity);
    }

    public void update(Long id, UserDto userDto) {
        UserEntity userEntity = userDAO.getById(id);
        userEntity.setName(userDto.getName());
        userEntity.setAge(userDto.getAge());
        userDAO.save(userEntity);
    }
}