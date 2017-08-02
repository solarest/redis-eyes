package com.solarest.rediseyes.service;

import com.solarest.rediseyes.exception.NonClientExcept;

import java.util.List;

/**
 * Created by JinJian on 17-8-2.
 */
public interface ClientOpsService {

    List<String> scanKeys(String conn, String pattern, Integer cursor, Integer size) throws NonClientExcept;
}
