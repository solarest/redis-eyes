package com.solarest.rediseyes.service;

import java.util.List;

/**
 * Created by JinJian on 17-8-2.
 */
public interface ClientOpsService {

    List<String> scanKeys(String conn, String pattern, Integer size);


}
