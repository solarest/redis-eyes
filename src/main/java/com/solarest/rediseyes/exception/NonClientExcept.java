package com.solarest.rediseyes.exception;

/**
 * Created by JinJian on 17-8-2.
 */
public class NonClientExcept extends Exception {

    public NonClientExcept(String conn) {
        super("This redis client has not been initialed !; connection is: " + conn);
    }
}
