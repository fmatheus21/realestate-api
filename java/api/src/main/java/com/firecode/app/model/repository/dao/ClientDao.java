package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.ClientEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.firecode.app.model.repository.ClientRepository;

@Component
public class ClientDao implements GenericDao<ClientEntity> {

    @Autowired
    private ClientRepository repository;

    @Override
    public ClientEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ClientEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public ClientEntity create(ClientEntity t) {
        return repository.save(t);
    }

    @Override
    public ClientEntity update(ClientEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    /*public Page<ClienteEntity> findAllPaginator(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "id");
        return repository.findAllPaginator(pageRequest);
    }*/
    public Page<ClientEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        return repository.findAllPaginator(filter, pageable);
    }

}
