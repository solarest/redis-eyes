package com.solarest.rediseyes.service.impl;

import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientExcept;
import com.solarest.rediseyes.service.ClientOpsService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JinJian on 17-8-2.
 */
@Service
public class ClientOpsServiceImpl implements ClientOpsService {

    @Override
    public List<String> scanKeys(String conn, String pattern, Integer cursor, Integer size) throws NonClientExcept {
        Jedis jedis = null;
        List<String> keys = new ArrayList<>();
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            for (int i = 0; keys.size() < size; i++) {
                ScanParams scanParams = new ScanParams().match(pattern).count((i + 1) * size);
                List<String> scanKeys = jedis.scan(cursor + (i * size), scanParams).getResult();
                for (String key : scanKeys) {
                    if (keys.size() == size) break;
                    keys.add(key);
                }
                if ((i + 1) * size >= countKeys(conn)) break;
            }
        } finally {
            client.releaseResource(jedis);
        }
        return keys;
    }

    @Override
    public Integer countKeys(String conn) throws NonClientExcept {
        Jedis jedis = null;
        Integer count;
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            count = Math.toIntExact(jedis.dbSize());
        } finally {
            client.releaseResource(jedis);
        }
        return count;
    }

    @Override
    public void removeKeys(String conn, String... k) throws NonClientExcept {
        Jedis jedis = null;
        RedisClient client = SingletonContainer.getSingleton().getRedisClient(conn);
        try {
            jedis = client.getResource();
            jedis.del(k);
        } finally {
            client.releaseResource(jedis);
        }
    }
}
