package com.epam.ilya.dao.connection;

/**
 * Class covers all exceptions connected with connection pool
 *
 * @author Ilya_Bondarenko
 */

public class ConnectionPoolException extends Exception {

    /**
     * Constructor call super class's constructor with the same parameters for throwing up
     *
     * @param message including information about exception
     * @param e caught exception
     */

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
