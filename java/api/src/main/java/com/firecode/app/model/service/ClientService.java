package com.firecode.app.model.service;

import com.firecode.app.model.entity.ClientEntity;
import com.firecode.app.model.repository.dao.ClientDao;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientDao dao;

    public Page<ClientEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        return dao.findAllPaginator(filter, pageable);
    }

    public ClientEntity findById(int id) {
        return dao.findById(id);
    }

}
