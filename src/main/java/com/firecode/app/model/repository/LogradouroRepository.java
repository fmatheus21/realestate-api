package com.firecode.app.model.repository;

import com.firecode.app.model.entity.LogradouroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogradouroRepository extends JpaRepository<LogradouroEntity, Integer> {

    LogradouroEntity findByCep(String value);

}
