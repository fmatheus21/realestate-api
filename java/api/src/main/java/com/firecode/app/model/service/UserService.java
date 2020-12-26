package com.firecode.app.model.service;

import com.firecode.app.model.entity.UserEntity;
import com.firecode.app.model.repository.dao.UserDao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public Page<UserEntity> findAllPaginator(Pageable pageable) {
        return dao.findAllPaginator(pageable);
    }

    public UserEntity findById(int id) {
        return dao.findById(id);
    }

    public Optional<UserEntity> findByUsername(String value) {
        return dao.findByUsername(value);
    }

}
