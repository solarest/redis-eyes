package com.solarest.rediseyes.exception;

/**
 * Created by JinJian on 17-8-2.
 */
public class NonClientException extends Exception {

    public NonClientException(String conn) {
        super("This redis client has not been initialed ! connection is: " + conn);
    }
}
