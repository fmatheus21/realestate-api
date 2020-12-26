package com.firecode.app.model.repository;

import com.firecode.app.model.entity.StreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<StreetEntity, Integer> {

    StreetEntity findByZipCode(String value);

}
