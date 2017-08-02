package com.solarest.rediseyes.exception;

/**
 * Created by JinJian on 17-7-31.
 * redis operation exception
 */
public class RedisOpsException extends Exception {

    public RedisOpsException(String conn, Exception e) {
        super("This redis operation failed!; connection is: " + conn + "Exception: " + e.getMessage());
    }

}
