package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.StreetEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.firecode.app.model.repository.StreetRepository;

@Component
public class StreetDao implements GenericDao<StreetEntity> {

    @Autowired
    private StreetRepository repository;

    @Override
    public StreetEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<StreetEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public StreetEntity create(StreetEntity t) {
        return repository.save(t);
    }

    @Override
    public StreetEntity update(StreetEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public StreetEntity findByZipCode(String value) {
        return repository.findByZipCode(value);
    }

}
