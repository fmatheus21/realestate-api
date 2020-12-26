package com.firecode.app.model.service;

import com.firecode.app.model.entity.StreetEntity;
import com.firecode.app.model.repository.dao.StreetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreetService {

    @Autowired
    private StreetDao dao;

    public StreetEntity findByZipCode(String value) {
        return dao.findByZipCode(value);
    }

}
