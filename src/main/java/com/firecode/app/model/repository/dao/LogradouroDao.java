package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.LogradouroEntity;
import com.firecode.app.model.repository.LogradouroRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class LogradouroDao implements GenericDao<LogradouroEntity> {

    @Autowired
    private LogradouroRepository repository;

    @Override
    public LogradouroEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<LogradouroEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public LogradouroEntity create(LogradouroEntity t) {
        return repository.save(t);
    }

    @Override
    public LogradouroEntity update(LogradouroEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public LogradouroEntity findByCep(String value) {
        return repository.findByCep(value);
    }

}
