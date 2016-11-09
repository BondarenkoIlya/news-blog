package com.epam.ilya.service;

/**
 * Class covers all exceptions connected with service
 *
 * @author Ilya_Bondarenko
 */

public class ServiceException extends Exception {

    /**
     * Constructor call super class's constructor with the same parameters for throwing up
     *
     * @param message including information about exception
     * @param e       caught exception
     */

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
