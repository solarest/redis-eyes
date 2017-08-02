package com.solarest.rediseyes.controller;

import com.solarest.rediseyes.client.RedisClient;
import com.solarest.rediseyes.client.RedisClientContainer;
import com.solarest.rediseyes.client.SingletonContainer;
import com.solarest.rediseyes.exception.NonClientExcept;
import com.solarest.rediseyes.service.ClientOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

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
        return clientOps.scanKeys(host + ":" + String.valueOf(port), pattern, start, size).toString();
    }
}
