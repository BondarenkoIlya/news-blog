package com.epam.ilya.dao.connection;

import com.epam.ilya.manager.PropertyManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolHolder.class);
    private static final String URL_NAME="url";
    private static final String USER_NAME="user";
    private static final String PASSWORD_NAME="password";
    private static final String DRIVER_NAME="driver";
    private static final String MAX_POOL_SIZE_NAME="max.pool.size";
    private static final String AUTO_COMMIT_NAME="max.pool.size";


    private static DataSource pool;

    public static DataSource getInstance() throws ConnectionPoolException {
        if (pool==null){
            HikariConfig config = new HikariConfig();
            try {
                Properties properties = PropertyManager.getProperty("database.properties");
                config.setJdbcUrl(properties.getProperty(URL_NAME));
                config.setUsername(properties.getProperty(USER_NAME));
                config.setPassword(properties.getProperty(PASSWORD_NAME));
                config.setDriverClassName(properties.getProperty(DRIVER_NAME));
                config.setMaximumPoolSize(Integer.parseInt(properties.getProperty(MAX_POOL_SIZE_NAME)));
                config.setAutoCommit(Boolean.parseBoolean(properties.getProperty(AUTO_COMMIT_NAME)));
                pool = new HikariDataSource(config);
                LOG.info("Initialization of Hikari connection pool");
            } catch (IOException e) {
                throw new ConnectionPoolException("Cannot get properties for pool",e);
            }
        }
        return pool;
    }
}
