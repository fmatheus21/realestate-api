package com.firecode.app.model.repository.dao;

import java.util.List;

public interface GenericDao<T> {

    abstract T create(T t);

    abstract T update(T t);

    abstract List<T> findAll(String orderBy);

    abstract T findById(int id);

    abstract void deleteById(int id);

}
