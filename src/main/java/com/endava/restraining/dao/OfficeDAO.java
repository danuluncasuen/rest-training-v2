package com.endava.restraining.dao;

import com.endava.restraining.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeDAO extends JpaRepository<OfficeEntity, Long> {
    @Query(value = "select * from office_entity LIMIT :limit", nativeQuery = true)
    List<OfficeEntity> findAll(@Param("limit") long limit);
}
