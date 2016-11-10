package com.epam.ilya.dao.jdbc;

import com.epam.ilya.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class contains main method needs for all dao
 *
 * @author Ilya_Bondarenko
 */

public abstract class DaoEntity {
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
