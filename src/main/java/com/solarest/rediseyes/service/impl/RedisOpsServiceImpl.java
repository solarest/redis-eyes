package com.solarest.rediseyes.service.impl;

import com.solarest.rediseyes.service.RedisOpsService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by JinJian on 17-7-28.
 */
public class RedisOpsServiceImpl implements RedisOpsService {
    @Override
    public void leftPush(Jedis jedis, String k, String... v) {
        
    }

    @Override
    public void rightPush(Jedis jedis, String k, String... v) {

    }

    @Override
    public void removeList(Jedis jedis, String k) {

    }

    @Override
    public void removeItem(Jedis jedis, String k, String... v) {

    }

    @Override
    public String leftPop(Jedis jedis, String k) {
        return null;
    }

    @Override
    public String rightPop(Jedis jedis, String k) {
        return null;
    }

    @Override
    public List<String> scanList(Jedis jedis, String k) {
        return null;
    }
}
