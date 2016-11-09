package com.epam.ilya.dao.hibernate;

import com.epam.ilya.dao.AbstractDaoFactory;
import com.epam.ilya.dao.Dao;
import com.epam.ilya.dao.jdbc.DaoEntity;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.model.BaseEntity;

public class HibernateDaoFactory extends AbstractDaoFactory {


    @Override
    public <T extends BaseEntity> Dao<T> getDao(Class<T> clazz) throws DaoException {
        return null;
    }

    @Override
    public void startTransaction() throws DaoException {

    }

    @Override
    public void commitTransaction() throws DaoException {

    }

    @Override
    public void rollbackTransaction() throws DaoException {

    }

    @Override
    public void close() throws DaoException {

    }
}
