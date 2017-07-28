package com.solarest.rediseyes.client.ops;

/**
 * Created by JinJian on 17-7-28.
 */
public interface ContainerRemoveOps {

    /**
     * destroy Jedis pool
     */
    void destroyPool();

    /**
     * remove Jedis pool from client container
     */
    void removeClient();

}
