package com.solarest.rediseyes.service;

import com.solarest.rediseyes.exception.NonClientException;

import java.util.List;

/**
 * Created by JinJian on 17-7-28.
 * redis queue operations
 */
public interface ListOpsService {

    void leftPush(String conn, String k, String... v) throws NonClientException;

    void rightPush(String conn, String k, String... v) throws NonClientException;

    void removeValue(String conn, String v) throws NonClientException;

    List<String> lRange(String conn, String k, Integer start, Integer stop) throws NonClientException;

    String leftPop(String conn, String k) throws NonClientException;

    String rightPop(String conn, String k) throws NonClientException;
}
