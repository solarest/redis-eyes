package com.solarest.rediseyes.client;

/**
 * @author JinJian
 * @date 17-7-28
 * Singleton for RedisClientContainer
 */
public class SingletonContainer {

    private SingletonContainer() {
    }

    public static RedisClientContainer getSingleton() {
        return SingletonInstance.container;
    }

    public static class SingletonInstance {
        static RedisClientContainer container = new RedisClientContainer();
    }
}


