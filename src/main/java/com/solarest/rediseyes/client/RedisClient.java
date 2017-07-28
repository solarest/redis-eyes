package com.solarest.rediseyes.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * Created by JinJian on 17-7-28.
 * redis connection pool
 */
public class RedisClient implements Serializable {

    private static JedisPool jedisPool = null;

    /**
     * create redisPool
     *
     * @return JedisPool
     */
    public static JedisPool initPool(String host, Integer port, String pwd) {
        if (jedisPool == null) {
            ContainerAddOps addOps = new ContainerAddOps() {
                @Override
                public void initPool() {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setTestOnBorrow(true);
                    config.setMaxIdle(5);       // the max amount of jedis instance in `idle` status
                    config.setMaxTotal(100);    // the max amount of jedis instance in `active` status
                    config.setMaxWaitMillis(100 * 1000);
                    jedisPool = new JedisPool(config, host, port, 5 * 1000, pwd);
                }

                @Override
                public void addContainer(String connInfo) {

                }
            };
        }
        return jedisPool;
    }

    /**
     * fetch jedis resource from jedisPool
     *
     * @return jedis instance
     */
    public synchronized static Jedis getResource() {
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
    public static void releaseResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * distory the jedisPool
     */
    public static void destoryPool() {
        jedisPool.close();
    }
}
