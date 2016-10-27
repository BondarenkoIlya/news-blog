package com.epam.ilya.dao.conection;

import com.zaxxer.hikari.HikariConfig;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    public static DataSource pool;

    public static DataSource getInstance() {
        if (pool==null){
            HikariConfig config = new HikariConfig();


        }
        return pool;
    }
}
