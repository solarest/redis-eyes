package com.solarest.rediseyes.controller;

import com.alibaba.fastjson.JSONObject;
import com.solarest.rediseyes.exception.NonClientException;
import com.solarest.rediseyes.exception.RedisOpsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;

/**
 * Created by JinJian on 17-8-3.
 */
public abstract class AbstractHandleController {

    @ResponseBody
    @ExceptionHandler
    public String exceptionHandle(Exception e) throws IOException {
        JSONObject exceptionJson = new JSONObject();
        e.printStackTrace();
        if (e instanceof NonClientException)
            exceptionJson.put("msg", e.getMessage());
        else if (e instanceof RedisOpsException)
            exceptionJson.put("msg", e.getMessage());
        else if (e instanceof JedisConnectionException)
            exceptionJson.put("msg", e.getMessage());
        else
            exceptionJson.put("msg", "fu@k! " + exceptionJson);
        exceptionJson.put("response_code", "500");
        return exceptionJson.toString();
    }

    protected String getJsonString(JSONObject contentJson) {
        contentJson.put("response_code", "200");
        contentJson.put("msg", "success");
        return contentJson.toString();
    }

    protected String getClientInfo(String host, Integer port) {
        return host + ":" + String.valueOf(port);
    }
}
