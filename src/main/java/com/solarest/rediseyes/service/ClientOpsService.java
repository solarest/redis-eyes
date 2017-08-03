package com.solarest.rediseyes.service;

import com.solarest.rediseyes.exception.NonClientException;

import java.util.List;

/**
 * Created by JinJian on 17-8-2.
 * redis client operations
 */
public interface ClientOpsService {

    List<String> scanKeys(String conn, String pattern, Integer cursor, Integer size) throws NonClientException;

    Integer countKeys(String conn) throws NonClientException;

    String keyType(String conn, String k) throws NonClientException;

    void removeKeys(String conn, String... k) throws NonClientException;

}
