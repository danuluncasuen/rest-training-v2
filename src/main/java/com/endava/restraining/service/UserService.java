package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.dao.WorkplaceDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.WorkplaceEntity;
import com.endava.restraining.entity.dto.LocationDto;
import com.endava.restraining.entity.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final WorkplaceDAO workplaceDAO;
    
    public void add(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        WorkplaceEntity workplace = workplaceDAO.getById(userDto.getWorkplaceId());
        user.setWorkplace(workplace);
        userDAO.save(user);
    }

    public List<UserDto> getAll() {
        List<UserEntity> userEntities = userDAO.findAll();
        List<UserDto> users = new ArrayList<>();
        userEntities.forEach(userEntity -> {
            UserDto userDto = new UserDto();
            userDto.setAge(userEntity.getAge());
            userDto.setName(userEntity.getName());
            userDto.setWorkplaceId(userEntity.getWorkplace().getId());
            users.add(userDto);
        });
        return users;
    }

    public UserDto getUserById(Long userId) {
        UserEntity userEntity = userDAO.getById(userId);
        UserDto userDto = new UserDto();
        userDto.setAge(userEntity.getAge());
        userDto.setName(userEntity.getName());
        userDto.setWorkplaceId(userEntity.getWorkplace().getId());
        return userDto;
    }

    public void delete(Long id) {
        userDAO.deleteById(id);
    }

    public void update(Long id, UserDto userDto) {
        UserEntity userEntity = userDAO.getById(id);
        userEntity.setAge(userDto.getAge());
        userEntity.setName(userDto.getName());
        userEntity.setWorkplace(workplaceDAO.getById(userDto.getWorkplaceId()));
        userDAO.save(userEntity);
    }

}
