package com.solarest.rediseyes.client;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

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

    public Jedis getJedisResource(String conn) {
        RedisClient client = this.getRedisClient(conn);
        return client.getResource();
    }

    public synchronized void addRedisClient(RedisClient client) {
        redisClients.put(client.getClientInfo().toJSONString(), client);
        logger.info("add to the redis client container success, connection is: " + client.getClientInfo().toJSONString());
    }

    public void removeClient(RedisClient client) {
        if (redisClients.containsKey(client.getClientInfo().toJSONString()))
            redisClients.remove(client.getClientInfo().toJSONString());
        logger.info("Remove from the redis client container success, connection is: " + client.getClientInfo().toJSONString());
    }

    public void removeClient(String host, Integer port) {
        JSONObject json = new JSONObject();
        json.put("host", host);
        json.put("port", port);
        if (redisClients.containsKey(json.toJSONString())) {
            redisClients.remove(json.toJSONString());
            logger.info("Remove from the redis client container success, connection is: " + json.toJSONString());
        } else {
            logger.warn("This redis client is not exist!");
        }
    }

    public JSONObject reportContainStatus() {
        JSONObject json = new JSONObject();
        json.put("count", redisClients.size());
        json.put("content", redisClients.keySet().stream().map(JSONObject::parseObject).collect(Collectors.toList()));
        return json;
    }
}
