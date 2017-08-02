package com.solarest.rediseyes.service;

import com.solarest.rediseyes.exception.NonClientExcept;

/**
 * Created by JinJian on 17-7-28.
 * redis queue operation
 */
public interface ListOpsService {

    void leftPush(String conn, String k, String... v) throws NonClientExcept;

    void rightPush(String conn, String k, String... v) throws NonClientExcept;

    String leftPop(String conn, String k) throws NonClientExcept;

    String rightPop(String conn, String k) throws NonClientExcept;
}
