package com.solarest.rediseyes.controller;

import com.alibaba.fastjson.JSONObject;
import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.RedisClientContainer;
import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientExcept;
import com.solarest.rediseyes.service.ClientOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by JinJian on 17-5-9.
 * client controller
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientOpsService clientOps;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String login(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            String password
    ) {
        RedisClient client = new RedisClient(host, port, password);
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.addRedisClient(client);
        return container.reportContainStatus().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/logoff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String logoff(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port
    ) {
        RedisClientContainer container = SingletonContainer.getSingleton();
        container.removeClient(host, port);
        return container.reportContainStatus().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/scan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String scanKeys(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            @RequestParam("pattern") String pattern,
            @RequestParam("start") Integer start,
            @RequestParam("size") Integer size
    ) throws NonClientExcept {
        JSONObject json = new JSONObject();
        String conn = host + ":" + String.valueOf(port);
        Integer count = clientOps.countKeys(conn);
        List<String> keyList = clientOps.scanKeys(conn, pattern, start, size);
        json.put("db_size", count);
        json.put("key_size", keyList.size());
        json.put("content", keyList);
        return json.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public String removeKey(
            @RequestParam("host") String host,
            @RequestParam("port") Integer port,
            @RequestParam("key") String key
    ) throws NonClientExcept {
        JSONObject json = new JSONObject();
        String conn = host + ":" + String.valueOf(port);
        clientOps.removeKeys(conn, key);
        json.put("msg", "success");
        return json.toString();
    }
}
