package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.ClienteEntity;
import com.firecode.app.model.repository.ClienteRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ClienteDao implements GenericDao<ClienteEntity> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public ClienteEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ClienteEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public ClienteEntity create(ClienteEntity t) {
        return repository.save(t);
    }

    @Override
    public ClienteEntity update(ClienteEntity t) {
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
    
    public Page<ClienteEntity> findAllPaginator(Pageable pageable) {        
        return repository.findAll(pageable);
    }
  

}
