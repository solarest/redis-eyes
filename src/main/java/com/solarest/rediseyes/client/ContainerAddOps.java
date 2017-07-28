package com.solarest.rediseyes.client;

/**
 * Created by JinJian on 17-7-28.
 */
public interface ContainerAddOps {

    /**
     * init jedisPool
     */
    void initPool();

    /**
     * add to container & add connInfo
     */
    void addClient();

}
