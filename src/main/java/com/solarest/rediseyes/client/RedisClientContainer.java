package com.solarest.rediseyes.client;

import com.alibaba.fastjson.JSONObject;
import com.solarest.rediseyes.exception.NonClientExcept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;

/**
 * Created by JinJian on 17-7-28.
 * The container with redis connection pools.
 * Must be the Singleton
 */
public class RedisClientContainer {

    private final Logger logger = LoggerFactory.getLogger(RedisClientContainer.class);

    private LinkedHashMap<String, RedisClient> redisClients;

    public RedisClientContainer() {
        this.redisClients = new LinkedHashMap<>();
    }

    public synchronized RedisClient getRedisClient(String conn) {
        return redisClients.get(conn);
    }

    public Jedis getJedisResource(String conn) throws NonClientExcept {
        RedisClient client = this.getRedisClient(conn);
        if (client == null)
            throw new NonClientExcept(conn);
        return client.getResource();
    }

    public synchronized void addRedisClient(RedisClient client) {
        redisClients.put(client.getClientInfo(), client);
        logger.info("add to the redis client container success, connection is: " + client.getClientInfo());
    }

    public void removeClient(RedisClient client) {
        if (redisClients.containsKey(client.getClientInfo())) {
            redisClients.remove(client.getClientInfo());
            logger.info("Remove from the redis client container success, connection is: " + client.getClientInfo());
        } else {
            logger.warn("This redis client is not exist!");
        }
    }

    public void removeClient(String host, Integer port) {
        String conn = host + ":" + String.valueOf(port);
        if (redisClients.containsKey(conn)) {
            redisClients.remove(conn);
            logger.info("Remove from the redis client container success, connection is: " + conn);
        } else {
            logger.warn("This redis client is not exist!");
        }
    }

    public JSONObject reportContainStatus() {
        JSONObject json = new JSONObject();
        json.put("count", redisClients.size());
        json.put("content", redisClients.keySet());
        return json;
    }
}
