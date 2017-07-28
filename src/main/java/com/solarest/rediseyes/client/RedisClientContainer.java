package com.solarest.rediseyes.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.LinkedHashMap;

/**
 * Created by JinJian on 17-7-28.
 * The container with redis connection pools.
 * Must be the Singleton
 */
public class RedisClientContainer {

    private final Logger logger = LoggerFactory.getLogger(RedisClientContainer.class);

    private LinkedHashMap<String, JedisPool> redisClients;

    public RedisClientContainer() {
        this.redisClients = new LinkedHashMap<>();
    }

    public synchronized JedisPool getRedisClient(String connInfo) {
        return redisClients.get(connInfo);
    }

    public Jedis getJedisResource(String connInfo) {
        JedisPool jedisPool = this.getRedisClient(connInfo);
        return jedisPool.getResource();
    }

    public void addRedisClient(String connInfo, JedisPool pool) {
        redisClients.put(connInfo, pool);
        logger.info("add to the RedisClientContainer success, connection info is: " + connInfo);
    }

    public void removeClient(String connInfo) {
        if (redisClients.containsKey(connInfo))
            redisClients.remove(connInfo);
    }
}
