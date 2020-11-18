package com.firecode.app.model.service;

import com.firecode.app.model.entity.ClienteEntity;
import com.firecode.app.model.repository.dao.ClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao dao;

    /*public Page<ClienteEntity> findAllPaginator(int page, int size) {
        return dao.findAllPaginator(page, size);
    }*/
    public Page<ClienteEntity> findAllPaginator(Pageable pageable) {
        return dao.findAllPaginator(pageable);
    }

    public ClienteEntity findById(int id) { 
        return dao.findById(id);
    }

}
