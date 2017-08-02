package com.solarest.rediseyes.service;

/**
 * Created by JinJian on 17-7-28.
 * redis queue operation
 */
public interface ListOpsService {

    void leftPush(String conn, String k, String... v);

    void rightPush(String conn, String k, String... v);

    String leftPop(String conn, String k);

    String rightPop(String conn, String k);
}
