package com.solarest.rediseyes.client;

import java.util.LinkedHashMap;

/**
 * Created by JinJian on 17-7-28.
 * The container with redis connection pools.
 * Must be Singleton
 */
public class RedisClientContainer {

    public RedisClientContainer() {
    }

    private LinkedHashMap<String, ? extends RedisClient> redisClients;

    public synchronized RedisClient getRedisClient(String connInfo) {
        return redisClients.get(connInfo);
    }

}
