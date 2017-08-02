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

    public synchronized RedisClient getRedisClient(String conn) throws NonClientExcept {
        RedisClient client = redisClients.get(conn);
        if (client == null)
            throw new NonClientExcept(conn);
        return client;
    }

    public Jedis getJedisResource(String conn) throws NonClientExcept {
        RedisClient client = this.getRedisClient(conn);
        return client.getResource();
    }

    public synchronized void addRedisClient(RedisClient client) {
        if (redisClients.containsKey(client.getClientInfo())) {
            logger.info("This client has been existed!");
        } else {
            redisClients.put(client.getClientInfo(), client);
            logger.info("Add the redis client into container successfully, connection is: " + client.getClientInfo());
        }
    }

    public synchronized void removeClient(RedisClient client) {
        if (redisClients.containsKey(client.getClientInfo())) {
            client.destroyPool();
            redisClients.remove(client.getClientInfo());
        } else {
            logger.warn("This redis client is not exist!");
        }
    }

    public synchronized void removeClient(String host, Integer port) {
        String conn = host + ":" + String.valueOf(port);
        if (redisClients.containsKey(conn)) {
            RedisClient client = redisClients.get(conn);
            client.destroyPool();
            redisClients.remove(conn);
            logger.info("Remove the redis client from container successfully, connection is: " + conn);
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
