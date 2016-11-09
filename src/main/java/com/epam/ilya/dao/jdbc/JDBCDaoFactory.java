package com.epam.ilya.dao.jdbc;

import com.epam.ilya.dao.AbstractDaoFactory;
import com.epam.ilya.dao.Dao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.dao.jdbc.connection.ConnectionPoolException;
import com.epam.ilya.dao.jdbc.connection.ConnectionPoolHolder;
import com.epam.ilya.model.BaseEntity;

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
    public <T extends BaseEntity> Dao<T> getDao(Class<T> clazz) throws DaoException {
        DaoEntity<T> daoEntity;
        try {
            String simpleName = clazz.getSimpleName();
            Package aPackage = this.getClass().getPackage();
            String daoName = aPackage+"."+ simpleName + "Dao";
            daoEntity = (DaoEntity<T>) Class.forName(daoName).newInstance();
            daoEntity.setConnection(connection);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DaoException("Cannot create new instance", e);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Cannot create class for name", e);
        }
        return daoEntity;
    }

    @Override
    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Cannot set auto commit- false", e);
        }
    }

    @Override
    public void commitTransaction() throws DaoException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Cannot set auto commit true", e);
        }
    }

    @Override
    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Cannot rollback transaction", e);
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
