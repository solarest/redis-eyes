package com.solarest.rediseyes.client;

/**
 * Created by JinJian on 17-7-28.
 * Singleton for RedisClient
 */
public class SingletonContainer {

    private static volatile RedisClientContainer container = null;

    private SingletonContainer() {
    }

    public static RedisClientContainer getSingleton() {
        if (container == null) {
            synchronized (RedisClientContainer.class) {
                if (container == null) {
                    container = new RedisClientContainer();
                }
            }
        }
        return container;
    }
}
