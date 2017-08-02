package com.solarest.rediseyes.client;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * Created by JinJian on 17-7-28.
 * redis connection pool
 */
public class RedisClient implements Serializable {

    private String host;

    private Integer port;

    private String password;

    private JedisPool jedisPool;

    public RedisClient(String host, Integer port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.createPool(host, port, password);
    }

    public JSONObject getClientInfo() {
        JSONObject json = new JSONObject();
        json.put("host", host);
        json.put("port", port);
        return json;
    }

    /**
     * create redisPool
     *
     * @return JedisPool
     */
    public void createPool(String host, Integer port, String password) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        config.setMaxIdle(5);       // the max amount of jedis instance in `idle` status
        config.setMaxTotal(100);    // the max amount of jedis instance in `active` status
        config.setMaxWaitMillis(100 * 1000);
        if (password != null && !"".equals(password)) {
            this.jedisPool = new JedisPool(config, host, port, 5 * 1000, password);
        } else {
            this.jedisPool = new JedisPool(config, host, port, 5 * 1000);
        }
    }

    /**
     * fetch jedis resource from jedisPool
     *
     * @return jedis instance
     */
    public synchronized Jedis getResource() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * release jedis resource
     *
     * @param jedis jedis instance
     */
    public void releaseResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * destroy the jedisPool
     */
    public void destoryPool() {
        jedisPool.close();
    }
}

