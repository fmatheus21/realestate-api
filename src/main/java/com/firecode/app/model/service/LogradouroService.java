package com.firecode.app.model.service;

import com.firecode.app.model.entity.LogradouroEntity;
import com.firecode.app.model.repository.dao.LogradouroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogradouroService {

    @Autowired
    private LogradouroDao dao;

    public LogradouroEntity findByCep(String value) {
        return dao.findByCep(value);
    }

}
