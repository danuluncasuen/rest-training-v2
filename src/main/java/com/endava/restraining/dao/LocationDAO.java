package com.endava.restraining.dao;

import com.endava.restraining.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDAO extends JpaRepository<LocationEntity, Long> {
}
