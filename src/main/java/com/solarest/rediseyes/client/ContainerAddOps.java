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
     * add to container and add connInfo
     * @param connInfo
     */
    void addContainer(String connInfo);

}
