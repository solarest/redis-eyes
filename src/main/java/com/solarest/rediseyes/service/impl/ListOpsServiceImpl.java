package com.solarest.rediseyes.service.impl;

import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.service.ListOpsService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by JinJian on 17-7-28.
 */
@Service
public class ListOpsServiceImpl implements ListOpsService {

    @Override
    public void leftPush(String connInfo, String k, String... v) {
        SingletonContainer.getSingleton()
                .getJedisResource(connInfo)
                .lpush(k, v);
    }

    @Override
    public void rightPush(String connInfo, String k, String... v) {
        SingletonContainer.getSingleton()
                .getJedisResource(connInfo)
                .rpush(k, v);
    }

    @Override
    public void removeList(String connInfo, String... k) {
        SingletonContainer.getSingleton()
                .getJedisResource(connInfo)
                .del(k);
    }

    @Override
    public String leftPop(String connInfo, String k) {
        return SingletonContainer.getSingleton()
                .getJedisResource(connInfo)
                .lpop(k);
    }

    @Override
    public String rightPop(String connInfo, String k) {
        return SingletonContainer.getSingleton()
                .getJedisResource(connInfo)
                .rpop(k);
    }
}

