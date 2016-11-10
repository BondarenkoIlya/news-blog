package com.epam.ilya.dao;

import com.epam.ilya.dao.hibernate.HibernateDaoFactory;
import com.epam.ilya.dao.jdbc.DaoEntity;
import com.epam.ilya.dao.jdbc.JDBCDaoFactory;

public abstract class AbstractDaoFactory implements AutoCloseable {

    public static AbstractDaoFactory getDaoFactory(DaoType type) throws DaoException {
        if (type == DaoType.HIBERNATE) {
            return new HibernateDaoFactory();
        } else {
            return new JDBCDaoFactory();
        }
    }

    public abstract <T extends DaoEntity> T getDao(Class<T> clazz) throws DaoException;

    public abstract void startTransaction() throws DaoException;

    public abstract void commitTransaction() throws DaoException;

    public abstract void rollbackTransaction() throws DaoException;

    @Override
    public abstract void close() throws DaoException;
}
