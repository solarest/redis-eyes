package com.solarest.rediseyes.service;

/**
 * Created by JinJian on 17-7-28.
 * redis 队列操作
 */
public interface ListOpsService {

    void leftPush(String connInfo, String k, String... v);

    void rightPush(String connInfo, String k, String... v);

    String leftPop(String connInfo, String k);

    String rightPop(String connInfo, String k);
}
