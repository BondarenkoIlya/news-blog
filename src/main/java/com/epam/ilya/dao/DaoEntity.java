package com.epam.ilya.dao;

import com.epam.ilya.dao.connection.ConnectionPoolException;
import com.epam.ilya.dao.connection.ConnectionPoolHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class contains main method needs for all dao
 *
 * @author Ilya_Bondarenko
 */

public abstract class DaoEntity {
    private DataSource connectionPool;

    /**
     * Constructor take instance of connection pool
     *
     * @throws DaoException
     */

    public DaoEntity() throws DaoException {
        try {
            connectionPool = ConnectionPoolHolder.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot get connection from pool", e);
        }
    }

    /**
     * Method get connection for work with database from pool
     *
     * @return connection for work with database
     * @throws SQLException
     */

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
}
