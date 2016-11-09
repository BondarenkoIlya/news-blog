package com.epam.ilya.dao.jdbc;

import com.epam.ilya.dao.Dao;
import com.epam.ilya.dao.DaoException;
import com.epam.ilya.model.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class contains main method needs for all dao
 *
 * @author Ilya_Bondarenko
 */

public abstract class DaoEntity<T extends BaseEntity> implements Dao<T> {
    private Connection connection;

    public DaoEntity() throws DaoException {
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
