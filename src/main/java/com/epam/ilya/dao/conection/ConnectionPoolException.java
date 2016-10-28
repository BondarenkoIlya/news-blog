package com.epam.ilya.dao.conection;

public class ConnectionPoolException extends Throwable {
    public ConnectionPoolException(String message, Exception e) {
        super(message,e);
    }
}
