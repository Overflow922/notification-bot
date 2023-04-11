package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.DbAdapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DbAdapterService {

    private final DbAdapterRepository dbAdapterRepository;

    @Autowired
    public DbAdapterService(DbAdapterRepository dbAdapterRepository) {
        this.dbAdapterRepository = dbAdapterRepository;
    }



}
