package com.endava.restraining.dao;

import com.endava.restraining.entity.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineDAO extends JpaRepository<DisciplineEntity, Long> {

    DisciplineEntity getDisciplineEntityByName(String disciplineName);

}
