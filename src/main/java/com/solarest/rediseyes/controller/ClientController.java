package com.solarest.rediseyes.controller;

import com.alibaba.fastjson.JSONObject;
import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.RedisClientContainer;
import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientException;
import com.solarest.rediseyes.service.ClientOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by JinJian on 17-5-9.
 * routes on redis client operations
 */
@RestController
@RequestMapping("/client")
public class ClientController extends AbstractHandleController {

    @Autowired
    private ClientOpsService clientOps;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String login(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            String password
    ) throws Exception {
        RedisClient client = new RedisClient(host, port, password);
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.addRedisClient(client);
        return getJsonString(container.reportContainStatus());
    }

    @ResponseBody
    @RequestMapping(value = "/logoff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String logoff(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port
    ) {
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.removeClient(host, port);
        return getJsonString(container.reportContainStatus());
    }

    @ResponseBody
    @RequestMapping(value = "/scan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String scanKeys(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            @RequestParam("pattern") String pattern,
            @RequestParam("start") Integer start,
            @RequestParam("size") Integer size
    ) throws NonClientException {
        JSONObject json = new JSONObject();
        Integer count = clientOps.countKeys(getClientInfo(host, port));
        List<String> keyList = clientOps.scanKeys(getClientInfo(host, port), pattern, start, size);
        json.put("db_size", count);
        json.put("key_size", keyList.size());
        json.put("content", keyList);
        return getJsonString(json);
    }

    @ResponseBody
    @RequestMapping(value = "/type", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String getKeyType(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            @RequestParam("key") String key
    ) throws NonClientException {
        JSONObject json = new JSONObject();
        clientOps.keyType(getClientInfo(host, port), key);
        return getJsonString(json);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String removeKey(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            @RequestParam("key") String key
    ) throws NonClientException {
        JSONObject json = new JSONObject();
        clientOps.removeKeys(getClientInfo(host, port), key);
        return getJsonString(json);
    }
}
