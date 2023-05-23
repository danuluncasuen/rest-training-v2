package com.endava.restraining.dao;

import com.endava.restraining.entity.WorkplaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface WorkplaceDAO extends JpaRepository<WorkplaceEntity, Long> {
    @Query("SELECT wr FROM WorkplaceEntity wr WHERE wr.office.id = :officeId")
    List<WorkplaceEntity> findAllByOfficeId(@Param("officeId") Long officeId);
}
