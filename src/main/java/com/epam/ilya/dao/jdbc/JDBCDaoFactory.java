package com.epam.ilya.dao.jdbc;

import com.epam.ilya.dao.AbstractDaoFactory;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.dao.jdbc.connection.ConnectionPoolException;
import com.epam.ilya.dao.jdbc.connection.ConnectionPoolHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends AbstractDaoFactory {
    private DataSource connectionPool;
    private Connection connection;

    public JDBCDaoFactory() throws DaoException {
        try {
            this.connectionPool = ConnectionPoolHolder.getInstance();
            this.connection = connectionPool.getConnection();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Cannot get pool instance", e);
        }
    }

    @Override
    public <T extends DaoEntity> T getDao(Class<T> clazz) throws DaoException {
        T t;
        try {
            t = clazz.newInstance();
            t.setConnection(connection);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DaoException("Cannot create new instance", e);
        }
        return t;
    }

    @Override
    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Cannot set auto commit- false",e);
        }
    }

    @Override
    public void commitTransaction() throws DaoException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Cannot set auto commit true",e);
        }
    }

    @Override
    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Cannot rollback transaction",e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Cannot close connection", e);
        }
    }
}
