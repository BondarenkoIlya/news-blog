package com.epam.ilya.action;

/**
 * Class covers all exceptions caught on action layer and throw it higher
 *
 * @author Ilya_Bondarenko
 */

public class ActionException extends Exception {

    /**
     * Constructor call super class's constructor with the same parameters for throwing up
     *
     * @param message including information about exception
     * @param e       caught exception
     */

    public ActionException(String message, Exception e) {
        super(message, e);
    }
}
