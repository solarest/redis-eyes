package com.solarest.rediseyes.client;

import com.solarest.rediseyes.exception.NonClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    public RedisClient(String host, Integer port, String password) throws Exception {
        this.host = host;
        this.port = port;
        this.password = password;
        this.createPool(host, port, password);
    }

    public String getClientInfo() {
        return host + ":" + String.valueOf(port);
    }

    /**
     * create redisPool
     *
     * @return JedisPool
     */
    public void createPool(String host, Integer port, String password) throws Exception {
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
        if (!jedisPool.getResource().isConnected())
            throw new NonClientException(this.getClientInfo());
    }

    /**
     * fetch jedis resource from jedisPool
     *
     * @return jedis instance
     */
    public synchronized Jedis getResource() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                logger.info("Get Jedis successfully! connection is: " + getClientInfo());
                return jedis;
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
            logger.info("Release Jedis successfully! connection is: " + getClientInfo());
        }
    }

    /**
     * destroy the jedisPool
     */
    public void destroyPool() {
        jedisPool.close();
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}

