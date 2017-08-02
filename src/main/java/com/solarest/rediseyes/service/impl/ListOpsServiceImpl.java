package com.solarest.rediseyes.service.impl;

import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientExcept;
import com.solarest.rediseyes.service.ListOpsService;
import org.springframework.stereotype.Service;

/**
 * Created by JinJian on 17-7-28.
 */
@Service
public class ListOpsServiceImpl implements ListOpsService {

    @Override
    public void leftPush(String conn, String k, String... v) throws NonClientExcept {
        SingletonContainer.getSingleton()
                .getJedisResource(conn)
                .lpush(k, v);
    }

    @Override
    public void rightPush(String conn, String k, String... v) throws NonClientExcept {
        SingletonContainer.getSingleton()
                .getJedisResource(conn)
                .rpush(k, v);
    }

    @Override
    public String leftPop(String conn, String k) throws NonClientExcept {
        return SingletonContainer.getSingleton()
                .getJedisResource(conn)
                .lpop(k);
    }

    @Override
    public String rightPop(String conn, String k) throws NonClientExcept {
        return SingletonContainer.getSingleton()
                .getJedisResource(conn)
                .rpop(k);
    }
}

