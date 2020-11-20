package com.firecode.app.model.service;

import com.firecode.app.model.entity.UsuarioEntity;
import com.firecode.app.model.repository.dao.UsuarioDao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao dao;

    public Page<UsuarioEntity> findAllPaginator(Pageable pageable) {
        return dao.findAllPaginator(pageable);
    }

    public UsuarioEntity findById(int id) {
        return dao.findById(id);
    }

    public Optional<UsuarioEntity> findByUsuario(String value) {
        return dao.findByUsuario(value);
    }

}
