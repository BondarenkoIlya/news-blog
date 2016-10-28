package com.epam.ilya.dao;

import com.epam.ilya.dao.connection.ConnectionPoolException;
import com.epam.ilya.dao.connection.ConnectionPoolHolder;

import javax.sql.DataSource;

public abstract class DaoEntity {
    private DataSource connectionPool;

    public DaoEntity() throws DaoException {
        try {
            connectionPool = ConnectionPoolHolder.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot get connection from pool", e);
        }
    }

}
