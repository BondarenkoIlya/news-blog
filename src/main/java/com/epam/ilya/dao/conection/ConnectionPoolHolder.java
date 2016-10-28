package com.epam.ilya.dao.conection;

import com.epam.ilya.manager.PropertyManager;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolHolder.class);

    private static DataSource pool;

    public static DataSource getInstance() throws ConnectionPoolException {
        if (pool==null){
            HikariConfig config = new HikariConfig();
            try {
                Properties properties = PropertyManager.getProperty("connection_pool.properties");
            } catch (IOException e) {
                throw new ConnectionPoolException("Cannot get properties for pool",e);
            }
            //config.setJdbcUrl();

        }
        return pool;
    }
}
