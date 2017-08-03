package com.solarest.rediseyes.service.impl;

import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientException;
import com.solarest.rediseyes.service.ListOpsService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by JinJian on 17-7-28.
 */
@Service
public class ListOpsServiceImpl implements ListOpsService {

    @Override
    public void leftPush(String conn, String k, String... v) throws NonClientException {
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        Jedis jedis = null;
        try {
            jedis = client.getResource();
            jedis.lpush(k, v);
        } finally {
            client.releaseResource(jedis);
        }
    }

    @Override
    public void rightPush(String conn, String k, String... v) throws NonClientException {
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        Jedis jedis = null;
        try {
            jedis = client.getResource();
            jedis.rpush(k, v);
        } finally {
            client.releaseResource(jedis);
        }
    }

    @Override
    public void removeValue(String conn, String v) throws NonClientException {
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        Jedis jedis = null;
        try{
            jedis = client.getResource();
        }finally {
            client.releaseResource(jedis);
        }
    }

    @Override
    public List<String> lRange(String conn, String k, Integer start, Integer stop) throws NonClientException {
        Jedis jedis = null;
        List<String> vList;
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            vList = jedis.lrange(k, start, stop);
        } finally {
            client.releaseResource(jedis);
        }
        return vList;
    }

    @Override
    public String leftPop(String conn, String k) throws NonClientException {
        String v;
        Jedis jedis = null;
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            v = jedis.lpop(k);
        } finally {
            client.releaseResource(jedis);
        }
        return v;
    }

    @Override
    public String rightPop(String conn, String k) throws NonClientException {
        String v;
        Jedis jedis = null;
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            v = jedis.rpop(k);
        } finally {
            client.releaseResource(jedis);
        }
        return v;
    }
}

