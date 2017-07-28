package com.solarest.rediseyes.service;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by JinJian on 17-7-28.
 */
public interface RedisOpsService {

    void leftPush(Jedis jedis, String k, String... v);

    void rightPush(Jedis jedis, String k, String... v);

    void removeList(Jedis jedis, String k);

    void removeItem(Jedis jedis, String k, String... v);

    String leftPop(Jedis jedis, String k);

    String rightPop(Jedis jedis, String k);

    List<String> scanList(Jedis jedis, String k);
}
