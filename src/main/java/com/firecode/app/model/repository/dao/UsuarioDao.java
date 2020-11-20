package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.UsuarioEntity;
import com.firecode.app.model.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDao implements GenericDao<UsuarioEntity> {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UsuarioEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UsuarioEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public UsuarioEntity create(UsuarioEntity t) {
        return repository.save(t);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<UsuarioEntity> findAllPaginator(Pageable pageable) {
        return repository.findAll(pageable);
    }

     public Optional<UsuarioEntity> findByUsuario(String value) {
        return repository.findByUsuario(value);
    }

}
